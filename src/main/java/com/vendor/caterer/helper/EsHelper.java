package com.vendor.caterer.helper;



import co.elastic.clients.elasticsearch._types.FieldSort;
import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.SortOptionsBuilders;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.query_dsl.BoolQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.QueryBuilders;
import co.elastic.clients.json.JsonData;
import com.vendor.caterer.enums.LogicalOperator;
import com.vendor.caterer.enums.Operator;
import com.vendor.caterer.model.EsFieldSort;
import com.vendor.caterer.model.Filter;
import com.vendor.caterer.model.Node;

import com.vendor.caterer.model.Rule;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

import static com.vendor.caterer.enums.Operator.CONTAINS;
import static com.vendor.caterer.enums.Operator.EQ;
import static com.vendor.caterer.enums.Operator.GT;
import static com.vendor.caterer.enums.Operator.GTE;
import static com.vendor.caterer.enums.Operator.LTE;
import static com.vendor.caterer.enums.Operator.IN;
import static com.vendor.caterer.enums.Operator.LT;
import static com.vendor.caterer.enums.Operator.MATCH;
import static com.vendor.caterer.enums.Operator.NE;
import static com.vendor.caterer.enums.Operator.SW;

@Component
public class EsHelper {
    public Query getEsQuery(Node node) {
        BoolQuery.Builder boolQuery = QueryBuilders.bool();
        if (node instanceof Filter filter) {
            return createQuery(filter.getFieldName(), filter.getOperator(), filter.getValues());
        }
        Rule rule = (Rule) node;
        List<Node> filters = rule.getFilters();
        List<Query> queries = filters.stream().map(this::getEsQuery).filter(Objects :: nonNull).toList();
        return (rule.getOperator().equals(LogicalOperator.AND) ? boolQuery.must(queries) :
                boolQuery.should(queries)).build()._toQuery();
    }

    private Query createQuery(String fieldName, Operator operator, List<String> values) {
        if (operator.equals(EQ)) {
            return QueryBuilders.match().field(fieldName).query(values.get(0)).build()._toQuery();
        } else if (operator.equals(GT)) {
           return QueryBuilders.range().field(fieldName).gt(JsonData.fromJson(values.get(0))).build()._toQuery();
       } else if (operator.equals(GTE)) {
            return QueryBuilders.range().field(fieldName).gte(JsonData.fromJson(values.get(0))).build()._toQuery();
        }else if (operator.equals(LT)) {
            return QueryBuilders.range().field(fieldName).lt(JsonData.fromJson(values.get(0))).build()._toQuery();
        } else if (operator.equals(LTE)) {
            return QueryBuilders.range().field(fieldName).lte(JsonData.fromJson(values.get(0))).build()._toQuery();
        } else if (operator.equals(IN)){
            return QueryBuilders.termsSet().field(fieldName).terms(values).build()._toQuery();
        } else if (operator.equals(CONTAINS)) {
            return QueryBuilders.wildcard().field("*" + fieldName + "*").value(values.get(0)).build()._toQuery();
        } else if (operator.equals(NE)) {
            return QueryBuilders.bool().mustNot(List.of(QueryBuilders.match().field(fieldName)
                    .query(values.get(0)).build()._toQuery())).build()._toQuery();
        } else if (operator.equals(SW)) {
            return QueryBuilders.prefix().field(fieldName).value(values.get(0)).build()._toQuery();
        } else if (operator.equals(MATCH)) {
            return QueryBuilders.match().fuzziness("3").field(fieldName).query(values.get(0)).build()._toQuery();
        } else {
            return null;
        }
    }

    public List<SortOptions> buildSortCriteria(List<EsFieldSort> esFieldSortList) {
        //TODO : Add support to GeoDistance sorting
        return esFieldSortList.stream().map(esFieldSort -> new SortOptions.Builder().field(SortOptionsBuilders.field().field(esFieldSort.getFieldIdentifier())
                .order(SortOrder.valueOf(esFieldSort.getSortDirection().toString())).build()).build()).toList();

    }
}

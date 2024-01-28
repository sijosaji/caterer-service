package com.vendor.caterer.services;

import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import com.vendor.caterer.dao.CatererRepository;
import com.vendor.caterer.dto.CatererCreateRequest;
import com.vendor.caterer.dto.CatererUpdateRequest;
import com.vendor.caterer.dto.SearchRequest;
import com.vendor.caterer.helper.EsHelper;
import com.vendor.caterer.mapper.CatererMapper;
import com.vendor.caterer.model.Caterer;
import com.vendor.caterer.model.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class CatererService {
    @Autowired
    private CatererRepository dao;
    @Autowired
    private EsHelper esHelper;
    @Autowired
    private ElasticsearchOperations elasticsearchOperations;
    public ResponseEntity<Caterer> saveCaterer(CatererCreateRequest createRequest) {
        Caterer caterer = convertCreateRequestModelToDocModel(createRequest);
        dao.save(caterer);
        return ResponseEntity.status(HttpStatus.CREATED).body(caterer);
    }

    private Caterer convertCreateRequestModelToDocModel(CatererCreateRequest createRequest) {
        Caterer caterer = CatererMapper.mapper.mapCreateRequestToModel(createRequest);

       UUID catererId = UUID.randomUUID();
       caterer.setId(catererId);
       if (Objects.isNull(createRequest.getBranchId())) {
            caterer.setBranchId(catererId);
       }
       caterer.setCreatedOn(LocalDateTime.now().toString());
       caterer.setLastUpdated(LocalDateTime.now().toString());
       return caterer;
    }

    private Caterer convertUpdateRequestModelToDocModel(Caterer caterer,CatererUpdateRequest catererUpdateRequest) {
        Caterer updatedCaterer = CatererMapper.mapper.mapUpdateRequestToModel(catererUpdateRequest,caterer);

        if (Objects.isNull(catererUpdateRequest.getBranchId())) {
            updatedCaterer.setBranchId(updatedCaterer.getId());
        }
        updatedCaterer.setLastUpdated(LocalDateTime.now().toString());
        return updatedCaterer;
    }

    public ResponseEntity<Caterer> getCaterer(UUID id){
        Optional<Caterer> restaurant = dao.findById(id);
        return restaurant.map(caterer -> ResponseEntity.status(HttpStatus.ACCEPTED).body(caterer))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    public ResponseEntity<Caterer> updateCaterer(UUID id, CatererUpdateRequest updateRequest) {
        Optional<Caterer> caterer = dao.findById(id);

        if (caterer.isPresent()) {
            Caterer updatedCaterer = convertUpdateRequestModelToDocModel(caterer.get(),updateRequest);
            dao.save(updatedCaterer);
            return ResponseEntity.status(HttpStatus.OK).body(updatedCaterer);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<Void> deleteCaterer(UUID id) {
        if (dao.existsById(id)) {
            dao.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public Pagination<Caterer> getCaterers(SearchRequest searchRequest) {
        Query query = esHelper.getEsQuery(searchRequest.getNode());
        List<SortOptions> sortOrderList = esHelper.buildSortCriteria(searchRequest.getEsFieldSortList());
        NativeQuery nativeQuery = new NativeQuery(new NativeQueryBuilder().withQuery(query).withSort(sortOrderList));
        nativeQuery.setPageable(PageRequest.of(searchRequest.getOffset(), searchRequest.getLimit()));
        SearchHits<Caterer> searchHits = elasticsearchOperations.search(nativeQuery, Caterer.class);
        return Pagination.<Caterer>builder().data(searchHits.getSearchHits().stream().map(SearchHit::getContent).toList())
                 .returnedCount(searchHits.getTotalHits()).limit(searchRequest.getLimit()).build();
    }
}

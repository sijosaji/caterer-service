package com.vendor.caterer.dto;

import com.vendor.caterer.model.EsFieldsSort;
import com.vendor.caterer.model.Node;
import lombok.Data;

import java.util.List;

@Data
public class SearchRequest {
    private Node node;
    private Integer offset;
    private Integer limit;
    private List<EsFieldsSort> esFieldSortList;
}

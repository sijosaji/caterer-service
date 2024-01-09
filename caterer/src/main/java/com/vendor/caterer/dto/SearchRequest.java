package com.vendor.caterer.dto;

import com.vendor.caterer.model.Node;
import lombok.Data;

@Data
public class SearchRequest {
    private Node node;
    private Integer offset;
    private Integer limit;
}
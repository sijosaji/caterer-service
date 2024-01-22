package com.vendor.caterer.model;

import lombok.Data;

@Data
public class EsFieldSort {
    private String fieldIdentifier;
    private SortDirection sortDirection;
    public static enum SortDirection {
        ASC,
        DESC;
    }
}

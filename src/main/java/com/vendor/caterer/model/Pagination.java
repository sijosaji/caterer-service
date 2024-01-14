package com.vendor.caterer.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Builder
@Data
public class Pagination<T> {
    private Integer limit;
    private Long returnedCount;
    private List<T> data;
}

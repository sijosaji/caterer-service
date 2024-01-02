package com.vendor.caterer.model;


import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.WriteTypeHint;

import java.util.List;
import java.util.UUID;
@Data
@Document(indexName = "restaurant", writeTypeHint = WriteTypeHint.FALSE)
public class Restaurant {
    @Id
    private UUID id;
    private String name;
    private UUID branchId;
    private String phoneNumber;
    private String website;
    private String emailId;
    private Pair<Double, Double> restaurantLocation;
    private List<MenuItem> items;
    private List<Package> packages;
}

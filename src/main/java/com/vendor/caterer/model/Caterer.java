package com.vendor.caterer.model;


import com.vendor.caterer.dto.CatererCreateRequest;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.WriteTypeHint;

import java.util.List;
import java.util.UUID;
@Data
@Document(indexName = "caterer", writeTypeHint = WriteTypeHint.FALSE)
public class Caterer {
    @Id
    private UUID id;
    private String name;
    private UUID branchId;
    private String phoneNumber;
    private String website;
    private String emailId;
    private Location catererLocation;
    private UUID menuId;//TODO: IS IT REQUIRED?
    private Integer avgRating;
    private List<Review> reviews;
    private String address;
    private String createdOn;
    private String lastUpdated;


}

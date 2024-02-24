package com.vendor.caterer.model;

import com.vendor.caterer.enums.CategoryType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document(collection = "category")
public class Category {

    @Id
    private UUID id;
    private String name;
    private String description;
    private UUID catererId;
    private String createdOn;
    private String lastUpdated;
    private CategoryType type;
    private UUID packageId;
}
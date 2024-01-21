package com.vendor.caterer.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.WriteTypeHint;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;
import java.util.UUID;

@Data
//@Document(indexName = "category", writeTypeHint = WriteTypeHint.FALSE)
@Document(collection = "category")
public class Category {

    @Id
    private UUID id;
    private String name;
    private String description;
    private UUID catererId;
    private String createdOn;
    private String lastUpdated;

}
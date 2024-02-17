package com.vendor.caterer.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;
@Data
@Document(collection = "tag")
public class Tag {
    @Id
    private UUID id;
    private String name;
    private String description;
    private String createdOn;
    private String lastUpdated;
}

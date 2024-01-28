package com.vendor.caterer.model;

import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Data
@Document(indexName = "menu-item")
public class MenuItem {
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private boolean isVeg;
    private boolean isDrink;
    private String imageUrl;
    private UUID catererId;
    private Set<UUID> tags;
    private Set<UUID> categoryIds;
    private Set<UUID> packageCategoryIds;
    private String createdOn;
    private String lastUpdated;
}

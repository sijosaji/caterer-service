package com.vendor.caterer.model;

import org.springframework.data.elasticsearch.annotations.Document;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Document(indexName = "food-item")
public class FoodItem {
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private boolean isVeg;
    private boolean isDrink;
    private String imageUrl;
    private List<String> tags;
    private Set<UUID> categoryIds;
    private Set<UUID> packageCategoryIds;
}

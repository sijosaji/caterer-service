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
    private Cuisine cuisine;  // Enum for cuisine type
    private List<String> ingredients;
    private boolean isVeg;
    private String imageUrl;

    private List<Tags> tags;// Tags associated with this Food item.

    private UUID catererId;
    private Set<CategoryType> categories;
    private UUID packageId;
}

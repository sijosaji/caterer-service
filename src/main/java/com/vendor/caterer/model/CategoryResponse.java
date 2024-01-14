package com.vendor.caterer.model;

import java.util.List;
import java.util.UUID;

public class CategoryResponse {

    private UUID categoryId;
    private String name;
    private String description;
    private List<FoodItem> foodItems;
}

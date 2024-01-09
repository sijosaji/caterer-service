package com.vendor.caterer.model;

import java.util.Set;
import java.util.UUID;

public class Category {

    private UUID id;
    private CategoryType name;
    private String description;
    private Set<FoodItem> foodItems;// food items that are part of this category.

}
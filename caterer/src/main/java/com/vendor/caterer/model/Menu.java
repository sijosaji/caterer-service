package com.vendor.caterer.model;

import java.util.List;
import java.util.UUID;

public class Menu {

    private UUID id;
    private UUID catererId;
    private List<Category> categoryList;
    private List<FoodItem> otherFoodItems;//food items that are not part of any specific categories.
}

package com.vendor.caterer.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Package {

    private UUID id;
    private PackageType name;
    private String description;
    private UUID catererId;
    private PackageType type;
    private BigDecimal price;
    private Set<Category> categories;
    private List<FoodItem> specificItems;
    private int minimumOrderQuantity; // Minimum number of people required to offer this package.
    private boolean isAvailable;
}
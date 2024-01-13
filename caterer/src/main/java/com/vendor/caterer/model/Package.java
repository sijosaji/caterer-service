package com.vendor.caterer.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Package {

    private UUID id;
    private String name;
    private String description;
    private UUID catererId;
    private int minimumOrderQuantity; // Minimum number of people required to offer this package.
    private boolean isAvailable;
}
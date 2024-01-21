package com.vendor.caterer.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class PackageUpdateRequest {

    private String name;
    private String description;
    private UUID catererId;
    private int minimumOrderQuantity; // Minimum number of people required to offer this package.
    private boolean isAvailable;
}

package com.vendor.caterer.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class PackageCreateRequest {

    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @NotNull
    private UUID catererId;
    @NotNull
    private int minimumOrderQuantity; // Minimum number of people required to offer this package.
    @NotNull
    private boolean isAvailable;
}

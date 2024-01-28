package com.vendor.caterer.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;

@Data
public class MenuItemCreateRequest {
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @NotNull
    private BigDecimal price;
    @NotNull
    private boolean isVeg;
    @NotNull
    private boolean isDrink;
    private String imageUrl;
    private UUID catererId;
    private Set<UUID> tags;
    @NotNull
    private Set<UUID> categoryIds;
    private Set<UUID> packageCategoryIds;
}

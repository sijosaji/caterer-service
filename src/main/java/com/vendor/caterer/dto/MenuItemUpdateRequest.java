package com.vendor.caterer.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
public class MenuItemUpdateRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private boolean isVeg;
    private boolean isDrink;
    private String imageUrl;
    private List<String> tagsToAdd;
    private List<String> tagsToRemove;
    private Set<UUID> categoryIdsToAdd;
    private Set<UUID> categoryIdsToRemove;
    private Set<UUID> packageCategoryIdsToAdd;
    private Set<UUID> packageCategoryIdsToRemove;

}

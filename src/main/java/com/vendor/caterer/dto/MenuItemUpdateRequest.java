package com.vendor.caterer.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class MenuItemUpdateRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private boolean isVeg;
    private boolean isDrink;
    private String imageUrl;
    private List<MenuItemAssignment> assignmentUpdates;
}

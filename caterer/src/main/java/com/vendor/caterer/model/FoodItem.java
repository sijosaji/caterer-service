package com.vendor.caterer.model;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class FoodItem extends MenuItem {
    private Boolean isVeg;
    private BigDecimal pricePerQuantity;
}

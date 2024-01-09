package com.vendor.caterer.model;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class FoodItem2 extends MenuItem {//TODO: RENAMED TO FoodItem2 since new FoodItem object is created.
    private Boolean isVeg;
    private BigDecimal pricePerQuantity;
}

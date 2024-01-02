package com.vendor.caterer.model;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

import java.util.UUID;
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = FoodItem.class, name = "food"),
        @JsonSubTypes.Type(value = DrinksItem.class, name = "drink")
})
@Data
public class MenuItem {
    private UUID id;
    private String name;
    private String imageUrl;
}

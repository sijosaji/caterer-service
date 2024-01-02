package com.vendor.caterer.dto;

import com.vendor.caterer.model.MenuItem;


import lombok.Data;
import lombok.NonNull;
import org.apache.commons.lang3.tuple.Pair;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.net.URI;
import java.util.List;
import java.util.UUID;
@Data
public class RestaurantCreateRequest {
    private UUID id;
    @NotEmpty
    private String name;
    private UUID branchId;
    private String phoneNumber;
    private URI website;
    @Email
    private String emailId;
    private Pair<Double, Double> restaurantLocation;
    private List<MenuItem> items;
}

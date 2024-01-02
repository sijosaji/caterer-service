package com.vendor.caterer.interfaces;

import com.vendor.caterer.dto.RestaurantCreateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("v1/restaurants")
public interface RestaurantResource {
    @PostMapping
    ResponseEntity addRestaurant(@RequestBody RestaurantCreateRequest createRequest);
}

package com.vendor.caterer.resources;

import com.vendor.caterer.dto.RestaurantCreateRequest;
import com.vendor.caterer.interfaces.RestaurantResource;
import com.vendor.caterer.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestaurantResourceImpl implements RestaurantResource {
    @Autowired
    private RestaurantService restaurantService;
    @Override
    public ResponseEntity addRestaurant(RestaurantCreateRequest createRequest) {
        return restaurantService.saveRestaurant(createRequest);
    }
}

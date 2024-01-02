package com.vendor.caterer.services;

import com.vendor.caterer.dao.RestaurantRepository;
import com.vendor.caterer.dto.RestaurantCreateRequest;
import com.vendor.caterer.model.Restaurant;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class RestaurantService {
    @Autowired
    private RestaurantRepository dao;
    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public ResponseEntity<Restaurant> saveRestaurant(RestaurantCreateRequest createRequest) {
        createRequest.getItems().forEach(menuItem -> menuItem.setId(UUID.randomUUID()));
        Restaurant restaurant = convertCreateRequestModelToDocModel(createRequest);
        dao.save(restaurant);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(restaurant);
    }

    private Restaurant convertCreateRequestModelToDocModel(RestaurantCreateRequest createRequest) {
        Restaurant restaurant =  MODEL_MAPPER.map(createRequest, Restaurant.class);
        UUID restaurantId = UUID.randomUUID();
        restaurant.setId(restaurantId);
        if (Objects.isNull(createRequest.getBranchId())) {
            restaurant.setBranchId(restaurantId);
        }
        return restaurant;
    }
}

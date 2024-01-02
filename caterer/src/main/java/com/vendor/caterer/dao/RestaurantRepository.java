package com.vendor.caterer.dao;

import com.vendor.caterer.model.Restaurant;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RestaurantRepository extends ElasticsearchRepository<Restaurant, UUID> {
}

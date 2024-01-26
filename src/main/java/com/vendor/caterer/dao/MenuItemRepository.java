package com.vendor.caterer.dao;

import com.vendor.caterer.model.MenuItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MenuItemRepository extends MongoRepository<MenuItem, UUID> {
}

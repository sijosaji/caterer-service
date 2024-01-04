package com.vendor.caterer.dao;

import com.vendor.caterer.model.Caterer;
import com.vendor.caterer.model.MenuItem;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MenuItemRepository extends ElasticsearchRepository<MenuItem, UUID> {
}

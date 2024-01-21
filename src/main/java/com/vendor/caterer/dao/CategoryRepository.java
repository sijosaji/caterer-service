package com.vendor.caterer.dao;

import com.vendor.caterer.model.Category;
import com.vendor.caterer.model.Caterer;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends MongoRepository<Category, UUID> {
}

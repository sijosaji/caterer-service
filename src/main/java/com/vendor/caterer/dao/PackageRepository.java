package com.vendor.caterer.dao;

import com.vendor.caterer.model.Package;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PackageRepository extends MongoRepository<Package, UUID> {
}

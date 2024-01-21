package com.vendor.caterer.interfaces;

import com.vendor.caterer.dto.CategoryCreateRequest;
import com.vendor.caterer.dto.CategoryUpdateRequest;
import com.vendor.caterer.dto.PackageCreateRequest;
import com.vendor.caterer.dto.PackageUpdateRequest;
import com.vendor.caterer.model.Category;
import com.vendor.caterer.model.Package;
import com.vendor.caterer.model.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RequestMapping("v1/packages")
public interface PackageResource {
    @PostMapping
    ResponseEntity<Package> addPackage(@RequestBody PackageCreateRequest createRequest);

    @GetMapping("/{id}")
    ResponseEntity<Package> getPackage(@PathVariable UUID id);

    @PutMapping("/{id}")
    ResponseEntity<Package> updatePackage(@PathVariable UUID id, @RequestBody PackageUpdateRequest updateRequest);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletePackage(@PathVariable UUID id);

    @GetMapping
    ResponseEntity<Pagination<Package>> getAllPackages(@RequestParam(value = "page", defaultValue = "0") int page,
                                                 @RequestParam(value = "size", defaultValue = "10") int size);
}

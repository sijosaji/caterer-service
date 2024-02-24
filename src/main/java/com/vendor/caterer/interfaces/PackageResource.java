package com.vendor.caterer.interfaces;

import com.vendor.caterer.constants.Constants;
import com.vendor.caterer.dto.PackageCopyRequest;
import com.vendor.caterer.dto.PackageCreateRequest;
import com.vendor.caterer.dto.PackageUpdateRequest;
import com.vendor.caterer.model.Package;
import com.vendor.caterer.model.Pagination;
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
    ResponseEntity<Pagination<Package>> getAllPackages(@RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page,
                                                       @RequestParam(value = "size", defaultValue = Constants.DEFAULT_PAGE_SIZE) int size);

    @PostMapping("/copy/{id}")
    ResponseEntity<Package> copyPackage(@PathVariable UUID id,@RequestBody PackageCopyRequest copyRequest);
}

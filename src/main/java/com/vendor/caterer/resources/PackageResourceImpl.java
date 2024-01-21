package com.vendor.caterer.resources;

import com.vendor.caterer.dto.PackageCreateRequest;
import com.vendor.caterer.dto.PackageUpdateRequest;
import com.vendor.caterer.interfaces.PackageResource;
import com.vendor.caterer.model.Package;
import com.vendor.caterer.model.Pagination;
import com.vendor.caterer.services.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class PackageResourceImpl implements PackageResource {
    @Autowired
    private PackageService packageService;
    @Override
    public ResponseEntity<Package> addPackage(PackageCreateRequest createRequest) {
        return packageService.savePackage(createRequest);
    }

    @Override
    public ResponseEntity<Package> getPackage(UUID id) {
        return packageService.getPackage(id);
    }

    @Override
    public ResponseEntity<Package> updatePackage(UUID id, PackageUpdateRequest updateRequest) {
        return packageService.updatePackage(id,updateRequest);
    }

    @Override
    public ResponseEntity<Void> deletePackage(UUID id) {
        return packageService.deletePackage(id);
    }

    @Override
    public ResponseEntity<Pagination<Package>> getAllPackages(int page, int size) {
        return packageService.getAllPackages(page, size);
    }
}

package com.vendor.caterer.services;

import com.vendor.caterer.dao.PackageRepository;
import com.vendor.caterer.dto.PackageCreateRequest;
import com.vendor.caterer.dto.PackageUpdateRequest;
import com.vendor.caterer.interfaces.PackageMapper;
import com.vendor.caterer.model.Category;
import com.vendor.caterer.model.Package;
import com.vendor.caterer.model.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class PackageService {
    @Autowired
    private PackageRepository dao;

    public ResponseEntity<Package> savePackage(PackageCreateRequest createRequest) {
        Package packageModel = convertCreateRequestModelToDocModel(createRequest);
        dao.save(packageModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(packageModel);
    }

    private Package convertCreateRequestModelToDocModel(PackageCreateRequest createRequest) {
        Package packageModel = PackageMapper.mapper.mapCreateRequestToModel(createRequest);

        UUID packageId = UUID.randomUUID();
        packageModel.setId(packageId);

        packageModel.setCreatedOn(LocalDateTime.now().toString());
        packageModel.setLastUpdated(LocalDateTime.now().toString());
        return packageModel;
    }

    private Package convertUpdateRequestModelToDocModel(Package packageModel, PackageUpdateRequest updateRequest) {
        Package updatedPackage = PackageMapper.mapper.mapUpdateRequestToModel(updateRequest, packageModel);
        updatedPackage.setLastUpdated(LocalDateTime.now().toString());
        return updatedPackage;
    }

    public ResponseEntity<Package> getPackage(UUID id) {
        Optional<Package> packageModel = dao.findById(id);
        return packageModel.map(p -> ResponseEntity.status(HttpStatus.OK).body(p))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    public ResponseEntity<Package> updatePackage(UUID id, PackageUpdateRequest updateRequest) {
        Optional<Package> packageModel = dao.findById(id);

        if (packageModel.isPresent()) {
            Package updatedPackage = convertUpdateRequestModelToDocModel(packageModel.get(), updateRequest);
            dao.save(updatedPackage);
            return ResponseEntity.status(HttpStatus.OK).body(updatedPackage);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<Void> deletePackage(UUID id) {
        if (dao.existsById(id)) {
            dao.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Pagination<Package>> getAllPackages(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Package> packagesPage = dao.findAll(pageable);
        Pagination<Package> response = Pagination.<Package>builder()
                .data(packagesPage.getContent())
                .returnedCount((long) packagesPage.getNumberOfElements())
                .limit(packagesPage.getSize()).build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

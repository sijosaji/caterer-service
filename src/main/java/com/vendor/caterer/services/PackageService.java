package com.vendor.caterer.services;

import com.vendor.caterer.dao.CategoryRepository;
import com.vendor.caterer.dao.MenuItemRepository;
import com.vendor.caterer.dao.PackageRepository;
import com.vendor.caterer.dto.PackageCopyRequest;
import com.vendor.caterer.dto.PackageCreateRequest;
import com.vendor.caterer.dto.PackageUpdateRequest;
import com.vendor.caterer.mapper.PackageMapper;
import com.vendor.caterer.model.Category;
import com.vendor.caterer.model.MenuItem;
import com.vendor.caterer.model.Package;
import com.vendor.caterer.model.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class PackageService {
    @Autowired
    private PackageRepository packageRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    @Autowired
    private MenuItemRepository menuItemRepo;

    public ResponseEntity<Package> savePackage(PackageCreateRequest createRequest) {
        Package packageModel = convertCreateRequestModelToDocModel(createRequest);
        packageRepo.save(packageModel);
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
        Optional<Package> packageModel = packageRepo.findById(id);
        return packageModel.map(p -> ResponseEntity.status(HttpStatus.OK).body(p))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    public ResponseEntity<Package> updatePackage(UUID id, PackageUpdateRequest updateRequest) {
        Optional<Package> packageModel = packageRepo.findById(id);

        if (packageModel.isPresent()) {
            Package updatedPackage = convertUpdateRequestModelToDocModel(packageModel.get(), updateRequest);
            packageRepo.save(updatedPackage);
            return ResponseEntity.status(HttpStatus.OK).body(updatedPackage);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<Void> deletePackage(UUID id) {
        if (packageRepo.existsById(id)) {
            packageRepo.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Pagination<Package>> getAllPackages(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Package> packagesPage = packageRepo.findAll(pageable);
        Pagination<Package> response = Pagination.<Package>builder()
                .data(packagesPage.getContent())
                .returnedCount((long) packagesPage.getNumberOfElements())
                .limit(packagesPage.getSize()).build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public ResponseEntity<Package> copyPackage(UUID existingPackageId, PackageCopyRequest copyRequest) {
        Optional<Package> currentPackageModel = packageRepo.findById(existingPackageId);

        if (currentPackageModel.isPresent()) {
            Package newPackageModel = convertCopyRequestModelToDocModel(copyRequest, currentPackageModel.get());
            packageRepo.save(newPackageModel);

            //1.Get all categories linked to the existing package id
            List<Category> existingCategories = categoryRepo.findAllByPackageId(existingPackageId);

            if (!CollectionUtils.isEmpty(existingCategories)) {
                //2.Copy categories linked to existing package and save them as new categories
                Set<UUID> newCategoryIds = copyCategoriesLinkedToPackage(existingCategories, newPackageModel.getId());

                //3.Get all menu items with the current categoryIds and add new categories.
                addNewCategoriesToMenuItems(existingCategories, newCategoryIds);
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(newPackageModel);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    private Set<UUID> copyCategoriesLinkedToPackage(List<Category> existingCategories, UUID newPackageId) {
        Set<UUID> newCategoryIds = new HashSet<>();
        existingCategories.forEach(category -> {
            UUID newCategoryId = UUID.randomUUID();
            category.setId(newCategoryId);
            category.setPackageId(newPackageId);
            categoryRepo.save(category);
            newCategoryIds.add(newCategoryId);
        });
        return newCategoryIds;
    }

    private void addNewCategoriesToMenuItems(List<Category> existingCategories, Set<UUID> newCategoryIds) {
        List<UUID> existingCategoryIds = existingCategories.stream().map(Category::getId).toList();
        List<MenuItem> menuItems = menuItemRepo.findByCategoryIdsIn(existingCategoryIds);
        menuItems.forEach(menuItem -> {
            Set<UUID> categoryIds = menuItem.getCategoryIds();
            if (categoryIds == null) {
                menuItem.setCategoryIds(new HashSet<>(newCategoryIds));
            } else {
                categoryIds.addAll(newCategoryIds);
            }
        });
    }

    private Package convertCopyRequestModelToDocModel(PackageCopyRequest copyRequest, Package currentPackage) {
        Package packageModel = PackageMapper.mapper.mapCopyRequestToModel(copyRequest, currentPackage);

        UUID packageId = UUID.randomUUID();
        packageModel.setId(packageId);

        packageModel.setCreatedOn(LocalDateTime.now().toString());
        packageModel.setLastUpdated(LocalDateTime.now().toString());
        return packageModel;
    }
}

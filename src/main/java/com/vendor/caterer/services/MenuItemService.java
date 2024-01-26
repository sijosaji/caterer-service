package com.vendor.caterer.services;

import com.vendor.caterer.dao.MenuItemRepository;
import com.vendor.caterer.dto.MenuItemCreateRequest;
import com.vendor.caterer.dto.MenuItemUpdateRequest;
import com.vendor.caterer.helper.CollectionUtils;
import com.vendor.caterer.interfaces.MenuItemMapper;
import com.vendor.caterer.model.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class MenuItemService {
    @Autowired
    private MenuItemRepository dao;
    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    public ResponseEntity<MenuItem> saveMenuItem(MenuItemCreateRequest createRequest) {
        MenuItem menuItem = convertCreateRequestModelToDocModel(createRequest);
        dao.save(menuItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(menuItem);
    }

    private MenuItem convertCreateRequestModelToDocModel(MenuItemCreateRequest createRequest) {
        MenuItem menuItem = MenuItemMapper.mapper.mapCreateRequestToModel(createRequest);

        UUID menuItemId = UUID.randomUUID();
        menuItem.setId(menuItemId);

        menuItem.setCreatedOn(LocalDateTime.now().toString());
        menuItem.setLastUpdated(LocalDateTime.now().toString());
        return menuItem;
    }

    public ResponseEntity<MenuItem> updateMenuItem(UUID id, MenuItemUpdateRequest updateRequest) {
        Optional<MenuItem> menuItem = dao.findById(id);
        if (menuItem.isPresent()) {
            MenuItem updatedMenuItem = convertUpdateRequestModelToDocModel(menuItem.get(), updateRequest);
            dao.save(updatedMenuItem);
            return ResponseEntity.status(HttpStatus.OK).body(updatedMenuItem);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    private MenuItem convertUpdateRequestModelToDocModel(MenuItem menuItem, MenuItemUpdateRequest updateRequest) {
        //MenuItem updatedMenuItem = MenuItemMapper.mapper.mapUpdateRequestToModel(updateRequest, menuItem);
        if (updateRequest == null) {
            return menuItem;
        }
        if (updateRequest.getName() != null) {
            menuItem.setName(updateRequest.getName());
        }
        if (updateRequest.getDescription() != null) {
            menuItem.setDescription(updateRequest.getDescription());
        }
        if (updateRequest.getPrice() != null) {
            menuItem.setPrice(updateRequest.getPrice());
        }
        if (updateRequest.getImageUrl() != null) {
            menuItem.setImageUrl(updateRequest.getImageUrl());
        }
        menuItem.setVeg(updateRequest.isVeg());
        menuItem.setDrink(updateRequest.isDrink());

        //handle tags
        if (CollectionUtils.isNotEmpty(updateRequest.getTagsToAdd())) {
            menuItem.getTags().addAll(updateRequest.getTagsToAdd());
        }
        if (CollectionUtils.isNotEmpty(updateRequest.getTagsToRemove())) {
            menuItem.getTags().removeAll(updateRequest.getTagsToRemove());
        }

        //handle category ids
        if (CollectionUtils.isNotEmpty(updateRequest.getCategoryIdsToAdd())) {
            menuItem.getCategoryIds().addAll(updateRequest.getCategoryIdsToAdd());
        }
        if (CollectionUtils.isNotEmpty(updateRequest.getCategoryIdsToRemove())) {
            menuItem.getCategoryIds().removeAll(updateRequest.getCategoryIdsToRemove());
        }

        //handle package category ids
        if (CollectionUtils.isNotEmpty(updateRequest.getPackageCategoryIdsToAdd())) {
            menuItem.getPackageCategoryIds().addAll(updateRequest.getPackageCategoryIdsToAdd());
        }
        if (CollectionUtils.isNotEmpty(updateRequest.getPackageCategoryIdsToRemove())) {
            menuItem.getPackageCategoryIds().removeAll(updateRequest.getPackageCategoryIdsToRemove());
        }
        menuItem.setLastUpdated(LocalDateTime.now().toString());
        return menuItem;
    }

    public ResponseEntity<MenuItem> getMenuItem(UUID id) {
        Optional<MenuItem> menuItem = dao.findById(id);
        return menuItem.map(item -> ResponseEntity.status(HttpStatus.OK).body(item))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    public ResponseEntity<Void> deleteMenuItem(UUID id) {
        if (dao.existsById(id)) {
            dao.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

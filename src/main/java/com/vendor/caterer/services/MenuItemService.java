package com.vendor.caterer.services;

import com.vendor.caterer.dao.MenuItemRepository;
import com.vendor.caterer.dto.MenuItemAssignment;
import com.vendor.caterer.dto.MenuItemCreateRequest;
import com.vendor.caterer.dto.MenuItemUpdateRequest;
import com.vendor.caterer.enums.Operation;
import com.vendor.caterer.mapper.MenuItemMapper;
import com.vendor.caterer.model.MenuItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
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
        MenuItem updatedMenuItem = MenuItemMapper.mapper.mapUpdateRequestToModel(updateRequest, menuItem);

        //To handle category ids, package category ids, and tags ids
        handleAssignmentUpdates(updatedMenuItem, updateRequest);
        updatedMenuItem.setLastUpdated(LocalDateTime.now().toString());
        return updatedMenuItem;
    }

    private void handleAssignmentUpdates(MenuItem menuItem, MenuItemUpdateRequest updateRequest) {
        // Process all updates and do operations based on the type
        updateRequest.getAssignmentUpdates().forEach(update -> {
            switch (update.getType()) {
                case TAGS:
                    addOrRemoveElements(menuItem.getTags(), update);
                    break;
                case CATEGORY:
                    addOrRemoveElements(menuItem.getCategoryIds(), update);
                    break;
//                case PACKAGE_CATEGORY:
//                    addOrRemoveElements(menuItem.getPackageCategoryIds(), update);
//                    break;
            }
        });
    }

    private void addOrRemoveElements(Collection<UUID> collection, MenuItemAssignment update) {
        if (update.getOperation().equals(Operation.ADD)) {
            collection.addAll(update.getIds());
        } else if (update.getOperation().equals(Operation.REMOVE)) {
            collection.removeAll(update.getIds());
        }
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

package com.vendor.caterer.resources;

import com.vendor.caterer.dto.MenuItemCreateRequest;
import com.vendor.caterer.dto.MenuItemUpdateRequest;
import com.vendor.caterer.interfaces.MenuItemResource;
import com.vendor.caterer.model.MenuItem;
import com.vendor.caterer.services.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class MenuItemResourceImpl implements MenuItemResource {
    @Autowired
    private MenuItemService menuItemService;

    @Override
    public ResponseEntity<MenuItem> addMenuItem(MenuItemCreateRequest createRequest) {
        return menuItemService.saveMenuItem(createRequest);
    }

    @Override
    public ResponseEntity<MenuItem> getMenuItem(UUID id) {
        return menuItemService.getMenuItem(id);
    }

    @Override
    public ResponseEntity<MenuItem> updateMenuItem(UUID id, MenuItemUpdateRequest updateRequest) {
        return menuItemService.updateMenuItem(id, updateRequest);
    }

    @Override
    public ResponseEntity<Void> deleteMenuItem(UUID id) {
        return menuItemService.deleteMenuItem(id);
    }
}

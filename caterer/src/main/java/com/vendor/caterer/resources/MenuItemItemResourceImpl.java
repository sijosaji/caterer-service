package com.vendor.caterer.resources;

import com.vendor.caterer.interfaces.MenuItemResource;
import com.vendor.caterer.model.MenuItem;
import com.vendor.caterer.services.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuItemItemResourceImpl implements MenuItemResource {
    @Autowired
    private MenuItemService menuItemService;
    @Override
    public ResponseEntity<MenuItem> addMenuItem(MenuItem createRequest) {
        return menuItemService.saveMenuItem(createRequest);
    }

//    @Override
//    public ResponseEntity<Caterer> getMenuItem(UUID id) {
//        return catererService.getCaterer(id);
//    }
//
//    @Override
//    public ResponseEntity<Caterer> updateMenuItem(UUID id, CatererUpdateRequest updateRequest) {
//        return catererService.updateCaterer(id,updateRequest);
//    }
//
//    @Override
//    public ResponseEntity<Void> deleteMenuItem(UUID id) {
//        return catererService.deleteCaterer(id);
//    }
}

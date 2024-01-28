package com.vendor.caterer.interfaces;

import com.vendor.caterer.dto.MenuItemCreateRequest;
import com.vendor.caterer.dto.MenuItemUpdateRequest;
import com.vendor.caterer.model.MenuItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RequestMapping("v1/menu-item")
public interface MenuItemResource {
    @PostMapping
    ResponseEntity<MenuItem> addMenuItem(@RequestBody MenuItemCreateRequest createRequest);

    @GetMapping("/{id}")
    ResponseEntity<MenuItem> getMenuItem(@PathVariable UUID id);

    @PutMapping("/{id}")
    ResponseEntity<MenuItem> updateMenuItem(@PathVariable UUID id, @RequestBody MenuItemUpdateRequest updateRequest);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteMenuItem(@PathVariable UUID id);

}

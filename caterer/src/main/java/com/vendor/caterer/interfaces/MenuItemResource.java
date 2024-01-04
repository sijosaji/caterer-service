package com.vendor.caterer.interfaces;

import com.vendor.caterer.model.MenuItem;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("v1/caterers/menu")
public interface MenuItemResource {
    @PostMapping
    ResponseEntity<MenuItem> addMenuItem(@RequestBody MenuItem createRequest);

//    @GetMapping("/{id}")
//    ResponseEntity<Caterer> getMenuItem(@PathVariable UUID id);
//
//    @PutMapping("/{id}")
//    ResponseEntity<Caterer> updateMenuItem(@PathVariable UUID id, @RequestBody CatererUpdateRequest updateRequest);
//
//    @DeleteMapping("/{id}")
//    ResponseEntity<Void> deleteMenuItem(@PathVariable UUID id);
}

package com.vendor.caterer.interfaces;

import com.vendor.caterer.dto.*;
import com.vendor.caterer.model.Category;
import com.vendor.caterer.model.CategoryResponse;
import com.vendor.caterer.model.Caterer;
import com.vendor.caterer.model.Pagination;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RequestMapping("v1/categories")
public interface CategoryResource {
    @PostMapping
    ResponseEntity<Category> addCategory(@RequestBody CategoryCreateRequest createRequest);

    @GetMapping("/{id}")
    ResponseEntity<Category> getCategory(@PathVariable UUID id);

    @PutMapping("/{id}")
    ResponseEntity<Category> updateCategory(@PathVariable UUID id, @RequestBody CategoryUpdateRequest updateRequest);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteCategory(@PathVariable UUID id);

    @GetMapping
    ResponseEntity<Pagination<Category>> getAllCategories(@RequestParam(value = "page", defaultValue = "0") int page,
                                                    @RequestParam(value = "size", defaultValue = "10") int size);
}

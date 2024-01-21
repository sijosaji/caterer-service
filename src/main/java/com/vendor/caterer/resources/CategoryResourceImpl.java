package com.vendor.caterer.resources;

import com.vendor.caterer.dto.CategoryCreateRequest;
import com.vendor.caterer.dto.CategoryUpdateRequest;
import com.vendor.caterer.interfaces.CategoryResource;
import com.vendor.caterer.model.Category;
import com.vendor.caterer.model.Pagination;
import com.vendor.caterer.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class CategoryResourceImpl implements CategoryResource {
    @Autowired
    private CategoryService categoryService;

    @Override
    public ResponseEntity<Category> addCategory(CategoryCreateRequest createRequest) {
        return categoryService.saveCategory(createRequest);
    }

    @Override
    public ResponseEntity<Category> getCategory(UUID id) {
        return categoryService.getCaterer(id);
    }

    @Override
    public ResponseEntity<Category> updateCategory(UUID id, CategoryUpdateRequest updateRequest) {
        return categoryService.updateCategory(id, updateRequest);
    }

    @Override
    public ResponseEntity<Void> deleteCategory(UUID id) {
        return categoryService.deleteCategory(id);
    }

    @Override
    public ResponseEntity<Pagination<Category>> getAllCategories(int page, int size) {
        return categoryService.getAllCategories(page, size);
    }

}

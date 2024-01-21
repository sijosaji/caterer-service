package com.vendor.caterer.resources;

import com.vendor.caterer.dto.*;
import com.vendor.caterer.interfaces.CategoryResource;
import com.vendor.caterer.interfaces.CatererResource;
import com.vendor.caterer.model.Category;
import com.vendor.caterer.model.Caterer;
import com.vendor.caterer.model.Pagination;
import com.vendor.caterer.services.CategoryService;
import com.vendor.caterer.services.CatererService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
    public ResponseEntity<Page<Category>> getAllCategories(int page, int size) {
        return categoryService.getAllCategories(page, size);
    }

}

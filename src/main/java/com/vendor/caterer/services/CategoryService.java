package com.vendor.caterer.services;

import com.vendor.caterer.dao.CategoryRepository;
import com.vendor.caterer.dto.CategoryCreateRequest;
import com.vendor.caterer.dto.CategoryUpdateRequest;
import com.vendor.caterer.interfaces.CategoryMapper;
import com.vendor.caterer.model.Category;
import com.vendor.caterer.model.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository dao;
    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    public ResponseEntity<Category> saveCategory(CategoryCreateRequest createRequest) {
        Category category = convertCreateRequestModelToDocModel(createRequest);
        dao.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);
    }

    private Category convertCreateRequestModelToDocModel(CategoryCreateRequest createRequest) {
        Category category = CategoryMapper.mapper.mapCreateRequestToModel(createRequest);

        UUID categoryId = UUID.randomUUID();
        category.setId(categoryId);

        category.setCreatedOn(LocalDateTime.now().toString());
        category.setLastUpdated(LocalDateTime.now().toString());
        return category;
    }

    private Category convertUpdateRequestModelToDocModel(Category category, CategoryUpdateRequest updateRequest) {
        Category updatedCaterer = CategoryMapper.mapper.mapUpdateRequestToModel(updateRequest, category);
        updatedCaterer.setLastUpdated(LocalDateTime.now().toString());
        return updatedCaterer;
    }

    public ResponseEntity<Category> getCaterer(UUID id) {
        Optional<Category> category = dao.findById(id);
        return category.map(cat -> ResponseEntity.status(HttpStatus.OK).body(cat))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    public ResponseEntity<Category> updateCategory(UUID id, CategoryUpdateRequest updateRequest) {
        Optional<Category> caterer = dao.findById(id);

        if (caterer.isPresent()) {
            Category updatedCategory = convertUpdateRequestModelToDocModel(caterer.get(), updateRequest);
            dao.save(updatedCategory);
            return ResponseEntity.status(HttpStatus.OK).body(updatedCategory);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public ResponseEntity<Void> deleteCategory(UUID id) {
        if (dao.existsById(id)) {
            dao.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Pagination<Category>> getAllCategories(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Category> categoriesPage = dao.findAll(pageable);
        Pagination<Category> response = Pagination.<Category>builder()
                .data(categoriesPage.getContent())
                .returnedCount((long) categoriesPage.getNumberOfElements())
                .limit(categoriesPage.getSize()).build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

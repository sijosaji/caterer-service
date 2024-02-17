package com.vendor.caterer.interfaces;

import com.vendor.caterer.constants.Constants;
import com.vendor.caterer.dto.CategoryUpdateRequest;
import com.vendor.caterer.dto.TagCreateRequest;
import com.vendor.caterer.dto.TagUpdateRequest;
import com.vendor.caterer.model.Pagination;
import com.vendor.caterer.model.Tag;

import jakarta.annotation.Nonnull;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@RequestMapping("v1/tags")
public interface TagResource {
    @PostMapping
    ResponseEntity<Tag> addTag(@RequestBody @Nonnull TagCreateRequest createRequest);

    @GetMapping("/{id}")
    ResponseEntity<Tag> getTag(@PathVariable UUID id);

    @PutMapping("/{id}")
    ResponseEntity<Tag> updateTag(@PathVariable UUID id, @RequestBody TagUpdateRequest updateRequest);
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteTag(@PathVariable UUID id);
    @GetMapping("list")
    ResponseEntity<Pagination<Tag>> getAllTags(@RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page,
                                                          @RequestParam(value = "size", defaultValue = Constants.DEFAULT_PAGE_SIZE) int size);

}

package com.vendor.caterer.services;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.vendor.caterer.dao.TagRepository;
import com.vendor.caterer.dto.TagCreateRequest;
import com.vendor.caterer.dto.TagUpdateRequest;
import com.vendor.caterer.mapper.TagMapper;
import com.vendor.caterer.model.Category;
import com.vendor.caterer.model.Pagination;
import com.vendor.caterer.model.Tag;


import org.springframework.data.domain.Pageable;

public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public ResponseEntity<Tag> saveTag(TagCreateRequest createRequest) {
        Tag tag = TagMapper.mapper.mapCreateRequestToModel(createRequest);
        tag.setId(UUID.randomUUID());
        tagRepository.save(tag);
        tag.setCreatedOn(LocalDateTime.now().toString());
        tag.setLastUpdated(LocalDateTime.now().toString());
        return ResponseEntity.ok(tag);
    }

    public ResponseEntity<Tag> getTag(UUID id) {
        Optional<Tag> tag = tagRepository.findById(id);
        return tag.map(t -> ResponseEntity.ok(t))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Tag> updateTag(UUID id, TagUpdateRequest updateRequest) {
        Optional<Tag> tag = tagRepository.findById(id);
        if (tag.isPresent()) {
            Tag updatedTag = TagMapper.mapper.mapUpdateRequestToModel(updateRequest, tag.get());
            updatedTag.setLastUpdated(LocalDateTime.now().toString());
            tagRepository.save(updatedTag);
            return ResponseEntity.ok(updatedTag);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Void> deleteTag(UUID id) {
        Optional<Tag> tag = tagRepository.findById(id);
        if (tag.isPresent()) {
            tagRepository.delete(tag.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<Pagination<Tag>> getAllTags(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Tag> tags = tagRepository.findAll(pageable);
        Pagination<Tag> response = Pagination.<Tag>builder()
                .data(tags.getContent())
                .returnedCount((long) tags.getNumberOfElements())
                .limit(tags.getSize()).build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
}

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
import com.vendor.caterer.model.Pagination;
import com.vendor.caterer.model.Tag;


import org.springframework.data.domain.Pageable;

public class TagService {

    @Autowired
    private TagRepository tagRepository;

    /**
     * Adds a new tag based on the provided TagCreateRequest.
     *
     * @param createRequest The request object containing the details of the tag to be created.
     * @return A ResponseEntity containing the created Tag object, or an error message if the tag could not be created.
     */
    public ResponseEntity<Tag> saveTag(TagCreateRequest createRequest) {
        Tag tag = TagMapper.mapper.mapCreateRequestToModel(createRequest);
        tag.setId(UUID.randomUUID());
        tag.setCreatedOn(LocalDateTime.now().toString());
        tag.setLastUpdated(LocalDateTime.now().toString());
        tagRepository.save(tag);
        return ResponseEntity.ok(tag);
    }

    /**
     * Retrieves a Tag object based on the provided UUID.
     *
     * @param id The UUID of the tag to be retrieved.
     * @return A ResponseEntity containing the Tag object, or an error message if the tag could not be found.
     */
    public ResponseEntity<Tag> getTag(UUID id) {
        Optional<Tag> tag = tagRepository.findById(id);
        return tag.map(t -> ResponseEntity.ok(t))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Updates an existing Tag object with the provided details.
     *
     * @param id The UUID of the tag to be updated.
     * @param updateRequest The request object containing the new details of the tag.
     * @return A ResponseEntity containing the updated Tag object, or an error message if the tag could not be updated.
     */
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

    /**
     * Deletes a Tag object based on the provided UUID.
     *
     * @param id The UUID of the tag to be deleted.
     * @return A ResponseEntity containing a success message if the tag was deleted, or an error message if the tag could not be found.
     */
    public ResponseEntity<Void> deleteTag(UUID id) {
        Optional<Tag> tag = tagRepository.findById(id);
        if (tag.isPresent()) {
            tagRepository.delete(tag.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Retrieves a list of all Tag objects.
     *
     * @param page The page number of the results to be retrieved.
     * @param size The number of results to be retrieved per page.
     * @return A ResponseEntity containing a Pagination object containing the list of Tag objects, or an error message if the tags could not be retrieved.
     */
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

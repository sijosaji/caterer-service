package com.vendor.caterer.resources;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.vendor.caterer.dto.TagCreateRequest;
import com.vendor.caterer.dto.TagUpdateRequest;
import com.vendor.caterer.interfaces.TagResource;
import com.vendor.caterer.model.Pagination;
import com.vendor.caterer.model.Tag;
import com.vendor.caterer.services.TagService;

public class TagResourceImpl implements TagResource {
    @Autowired
    private TagService tagService;

    /**
    * Adds a new tag based on the provided TagCreateRequest.
    *
    * @param createRequest The request object containing the details of the tag to be created.
    * @return A ResponseEntity containing the created Tag object, or an error message if the tag could not be created.
    */
    @Override
    public ResponseEntity<Tag> addTag(TagCreateRequest createRequest) {
        return tagService.saveTag(createRequest);
        
    }

    /**
    * Retrieves a Tag object based on the provided UUID.
    *
    * @param id The UUID of the tag to be retrieved.
    * @return A ResponseEntity containing the Tag object, or an error message if the tag could not be found.
     */
    @Override
    public ResponseEntity<Tag> getTag(UUID id) {
        return tagService.getTag(id);
    }

    @Override
    public ResponseEntity<Tag> updateTag(UUID id, TagUpdateRequest updateRequest) {
        return tagService.updateTag(id, updateRequest);
    }

    @Override
    public ResponseEntity<Void> deleteTag(UUID id) {
       return tagService.deleteTag(id);
    }

    @Override
    public ResponseEntity<Pagination<Tag>> getAllTags(int page, int size) {
        return tagService.getAllTags(page, size);
    }
    
}

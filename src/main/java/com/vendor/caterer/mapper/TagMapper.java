package com.vendor.caterer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import com.vendor.caterer.dto.TagCreateRequest;
import com.vendor.caterer.dto.TagUpdateRequest;
import com.vendor.caterer.model.Tag;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TagMapper {
    TagMapper mapper = Mappers.getMapper(TagMapper.class);
    Tag mapCreateRequestToModel(TagCreateRequest createRequest);
    Tag mapUpdateRequestToModel(TagUpdateRequest updateRequest, @MappingTarget Tag tag);
}

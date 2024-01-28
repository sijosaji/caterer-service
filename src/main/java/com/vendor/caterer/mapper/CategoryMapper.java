package com.vendor.caterer.mapper;

import com.vendor.caterer.dto.CategoryCreateRequest;
import com.vendor.caterer.dto.CategoryUpdateRequest;
import com.vendor.caterer.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CategoryMapper {

    CategoryMapper mapper = Mappers.getMapper(CategoryMapper.class);
    Category mapCreateRequestToModel(CategoryCreateRequest createRequest);
    Category mapUpdateRequestToModel(CategoryUpdateRequest dto, @MappingTarget Category entity);
}
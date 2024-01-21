package com.vendor.caterer.interfaces;

import com.vendor.caterer.dto.*;
import com.vendor.caterer.model.Category;
import com.vendor.caterer.model.Caterer;
import com.vendor.caterer.model.Package;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CatererMapper {

    CatererMapper mapper = Mappers.getMapper(CatererMapper.class);
    Caterer mapCreateRequestToModel(CatererCreateRequest createRequest);
    Caterer mapUpdateRequestToModel(CatererUpdateRequest dto, @MappingTarget Caterer entity);
    Category mapCreateRequestToModel(CategoryCreateRequest createRequest);
    Category mapUpdateRequestToModel(CategoryUpdateRequest dto, @MappingTarget Category entity);
    Package mapCreateRequestToModel(PackageCreateRequest createRequest);
    Package mapUpdateRequestToModel(PackageUpdateRequest dto, @MappingTarget Package entity);
}

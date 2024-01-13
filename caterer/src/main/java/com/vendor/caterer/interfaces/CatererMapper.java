package com.vendor.caterer.interfaces;

import com.vendor.caterer.dto.CatererCreateRequest;
import com.vendor.caterer.dto.CatererUpdateRequest;
import com.vendor.caterer.model.Caterer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CatererMapper {

    public static final CatererMapper mapper = Mappers.getMapper(CatererMapper.class);
    Caterer mapCreateRequestToModel(CatererCreateRequest createRequest);
    Caterer mapUpdateRequestToModel(CatererUpdateRequest dto, @MappingTarget Caterer entity);

}

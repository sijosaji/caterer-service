package com.vendor.caterer.mapper;

import com.vendor.caterer.dto.MenuItemCreateRequest;
import com.vendor.caterer.dto.MenuItemUpdateRequest;
import com.vendor.caterer.model.MenuItem;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MenuItemMapper {

    MenuItemMapper mapper = Mappers.getMapper(MenuItemMapper.class);
    MenuItem mapCreateRequestToModel(MenuItemCreateRequest createRequest);
    MenuItem mapUpdateRequestToModel(MenuItemUpdateRequest dto, @MappingTarget MenuItem entity);
}
package com.vendor.caterer.interfaces;

import com.vendor.caterer.dto.MenuItemCreateRequest;
import com.vendor.caterer.model.MenuItem;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MenuItemMapper {
    MenuItemMapper mapper = Mappers.getMapper(MenuItemMapper.class);

    MenuItem mapCreateRequestToModel(MenuItemCreateRequest createRequest);

   // MenuItem mapUpdateRequestToModel(MenuItemUpdateRequest dto, @MappingTarget MenuItem entity);
}
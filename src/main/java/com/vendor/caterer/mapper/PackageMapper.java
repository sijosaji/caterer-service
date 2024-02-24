package com.vendor.caterer.mapper;

import com.vendor.caterer.dto.PackageCopyRequest;
import com.vendor.caterer.dto.PackageCreateRequest;
import com.vendor.caterer.dto.PackageUpdateRequest;
import com.vendor.caterer.model.Package;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PackageMapper {

    PackageMapper mapper = Mappers.getMapper(PackageMapper.class);
    Package mapCreateRequestToModel(PackageCreateRequest createRequest);
    Package mapUpdateRequestToModel(PackageUpdateRequest dto, @MappingTarget Package entity);
    Package mapCopyRequestToModel(PackageCopyRequest copyRequest, @MappingTarget Package entity);
}
package com.vendor.caterer.dto;

import com.vendor.caterer.enums.UserType;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Data
public class PackageCopyRequest {

    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    private int minimumOrderQuantity;
    private boolean isAvailable;
    private UUID userId;
    private UserType createdBy = UserType.CATERER;
}

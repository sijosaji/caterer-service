package com.vendor.caterer.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class CategoryUpdateRequest {

    private String name;
    private String description;
    private UUID catererId;
}

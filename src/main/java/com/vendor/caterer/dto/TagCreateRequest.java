package com.vendor.caterer.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class TagCreateRequest {

    @NotEmpty
    private String name;
    private String description;
}

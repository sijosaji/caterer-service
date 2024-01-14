package com.vendor.caterer.model;

import java.util.List;
import java.util.UUID;

public class PackageResponse {

    private UUID packageId;
    private String name;
    private String description;
    private List<CategoryResponse> categories;
}

package com.vendor.caterer.model;

import java.util.List;
import java.util.UUID;

public class Menu {

    private UUID catererId;
    private List<CategoryResponse> generalMenu;
    private List<PackageResponse> packages;
}

package com.vendor.caterer.model;

import com.vendor.caterer.enums.UserType;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.UUID;
@Data
@Document(collection = "package")
public class Package {

    private UUID id;
    private String name;
    private String description;
    private UUID catererId;
    private int minimumOrderQuantity; // Minimum number of people required to offer this package.
    private boolean isAvailable;
    private UserType createdBy;
    private UUID userId;
    private String createdOn;
    private String lastUpdated;
}
package com.vendor.caterer.model;

import lombok.Builder;
import lombok.Data;
import java.util.UUID;

@Data
@Builder
public class Review {
    private UUID id;
    private String message;
    private Integer rating;
    private String reviewedBy;
    private String timeStamp;
}

package com.vendor.caterer.dto;

import com.vendor.caterer.model.Location;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.net.URI;
import java.util.UUID;

@Data
public class CatererUpdateRequest {

    @NotEmpty
    private String name;
    private UUID branchId;
    private String phoneNumber;
    private URI website;
    @Email
    private String emailId;
    private Location catererLocation;
    private String address;
}

package com.vendor.caterer.dto;

import com.vendor.caterer.model.Location;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.net.URI;
import java.util.UUID;

@Data
public class CatererCreateRequest {
    @NotEmpty(message = "Name must be provided")
    private String name;

    private UUID branchId;

    @NotEmpty(message = "Phone number must be provided")
    @Pattern(regexp = "^\\+91\\d{10}$", message = "The provided Phone Number is invalid.")
    private String phoneNumber;

    private String website;
    @Email
    @NotEmpty(message = "Email Id must be provided.")
    private String emailId;

    private Location catererLocation;

    @NotEmpty(message = "Address must be provided")
    private String address;
}

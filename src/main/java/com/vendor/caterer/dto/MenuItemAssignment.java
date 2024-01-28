package com.vendor.caterer.dto;

import com.vendor.caterer.enums.Operation;
import com.vendor.caterer.enums.UpdateType;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class MenuItemAssignment {
    private Operation operation;
    private UpdateType type;
    private Set<UUID> ids;
}

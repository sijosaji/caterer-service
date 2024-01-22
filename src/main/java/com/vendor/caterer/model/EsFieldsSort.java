package com.vendor.caterer.model;

import co.elastic.clients.elasticsearch._types.SortOrder;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = GeoLocationSort.class, name = "geolocation"),
})
@Data
public class EsFieldsSort {
    private String fieldIdentifier;
    private SortOrder sortDirection;
}

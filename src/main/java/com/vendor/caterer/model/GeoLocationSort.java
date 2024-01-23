package com.vendor.caterer.model;

import co.elastic.clients.elasticsearch._types.SortOrder;
import lombok.Data;

@Data
public class GeoLocationSort extends EsFieldsSort {
    private Double longitude;
    private Double latitude;

}

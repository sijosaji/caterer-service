package com.vendor.caterer.model;

import com.vendor.caterer.VolumeMetric;
import lombok.Data;

import java.util.Map;


@Data
public class DrinksItem extends MenuItem {
    private VolumeMetric metric;
    Map<Double, Double> priceOnDifferentVolumes;
    private boolean isAlcoholic;
}

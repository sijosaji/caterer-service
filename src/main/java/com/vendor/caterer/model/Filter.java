package com.vendor.caterer.model;


import com.vendor.caterer.enums.Operator;
import lombok.Data;

import java.util.List;
@Data
public class Filter extends Node {
    private String fieldName;
    private List<String> values;
    private Operator operator;
}

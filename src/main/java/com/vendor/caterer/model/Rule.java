package com.vendor.caterer.model;

import com.vendor.caterer.enums.LogicalOperator;
import lombok.Data;

import java.util.List;
@Data
public class Rule extends Node {
    private LogicalOperator operator;
    private List<Node> filters;
}

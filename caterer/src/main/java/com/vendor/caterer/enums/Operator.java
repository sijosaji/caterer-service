package com.vendor.caterer.enums;

public enum Operator {
    EQ("eq"), // Equals
    NE("ne"), // NotEquals
    LT("lt"), // Less Than
    GT("gt"), // Greater Than
    GTE("gte"), // Greater Than Equal
    LTE("lte"), // Less Than Equal
    CONTAINS("contains"), // Contains
    SW("sw"), // Starts With
    IN("in"), // IN
    MATCH("match"); // Match

    Operator(String match) {
    }
}

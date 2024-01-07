package com.vendor.caterer.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Filter.class, name = "filter"),
        @JsonSubTypes.Type(value = Rule.class, name = "rule")
})
public abstract class Node {
}

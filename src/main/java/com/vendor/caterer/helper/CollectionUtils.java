package com.vendor.caterer.helper;

import java.util.Collection;

public class CollectionUtils {

    public static boolean isNotEmpty(Collection<?> collection) {
        return collection != null && !collection.isEmpty();
    }}

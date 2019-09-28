package com.plash.configurator.utils;

import java.util.concurrent.atomic.AtomicLong;

public class Utils {

    public static String nonNull(String str) {

        return str != null ? str : "";
    }

    public static synchronized Long getUniqueID() {
        AtomicLong counter = new AtomicLong(System.currentTimeMillis());
        Long value = (counter.incrementAndGet());
        return value;
    }

}

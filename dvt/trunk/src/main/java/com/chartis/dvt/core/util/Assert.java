package com.chartis.dvt.core.util;

public class Assert {

    private Assert() {
        
    }

    public static void notNull(final Object o) {
        if (o == null) {
            throw new AssertionError("object could not be null");
        }
    }
}

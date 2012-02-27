package com.chartis.dvt.commons.utils;

/**
 * 
 * @author CHAMINDA.AMARASINGHE
 *
 */
public class Assert {
    
    private Assert() {
        
    }

    public static void notNull(final Object o) {
        if (o == null) {
            throw new AssertionError("object could not be null");
        }
    }
}

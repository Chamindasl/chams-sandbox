package com.chartis.dvt.commons.utils;

/**
 * 
 * @author CHAMINDA.AMARASINGHE
 * 
 */
public class StringUtils {

    /**
     * only private cons
     */
    private StringUtils() {

    }

    public static String catAsIs(final String... objs) {
        final StringBuffer sb = new StringBuffer();
        for (String s : objs) {
            sb.append(s);
        }
        return sb.toString();
    }

    /**
     * to check all text has length
     * 
     * @param ss
     * @return
     */
    public static boolean allHasText(final String... ss) {
        for (String s : ss) {
            if (hasText(s)) {
                return false;
            }
        }
        return true;
    }

    /**
     * to check all text has length
     * 
     * @param ss
     * @return
     */
    public static boolean anyHasText(final String... ss) {
        for (String s : ss) {
            if (hasText(s)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasText(final String s) {
        if (s != null && !s.trim().isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * empty string if null
     * 
     * @param text
     *            text need to be checked
     * @return "" or text
     */
    public static String nullSafeText(final String text) {
        if (text == null) {
            return "";
        }
        return text;
    }

    /**
     * a safe way to concat strings.
     * 
     * @param os
     * @return
     */
    public static String cat(final Object... os) {
        final StringBuffer sb = new StringBuffer();
        for (Object o : os) {
            if (o instanceof Object[]) {
                buildToToString(sb, o);
                sb.append("[");
                for (Object oo : (Object[]) o) {
                    buildToToString(sb, oo);
                    sb.append(",");
                }
                sb.append("]");
            } else {
                buildToToString(sb, o);
            }
        }
        return sb.toString();
    }

    private static void buildToToString(final StringBuffer sb, Object o) {
        if (o == null) {
            sb.append("[null]");
        } else if (!allHasText(o.toString())) {
            sb.append("[empty]");
        } else {
            sb.append(o.toString());
        }
        sb.append(" ");
    }
}

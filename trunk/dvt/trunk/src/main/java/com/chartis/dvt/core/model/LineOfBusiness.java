package com.chartis.dvt.core.model;

public enum LineOfBusiness {

    AUTO("AUTO"),
    TRAVEL("TRAVEL"),
    COMMERCIAL_LINES("COMMERCIAL LINES"),
    PERSONAL_LINES("PERSONAL_LINES"),
    COMMON("COMMON");

    public static LineOfBusiness byCode(int code) {
        switch (code) {
        case 1:
        case 2:
            return AUTO;
        case 6:
        case 8:
        case 9:
        case 23:
        case 26:
        case 13:
            return COMMERCIAL_LINES;
        case 19:
            return PERSONAL_LINES;
        default:
            return COMMON;
        }
    }

    private String name;
    
    private LineOfBusiness(final String name) {
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

}

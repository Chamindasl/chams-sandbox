package com.chartis;

public class XmlUtils {

    public static String parentXpath(String xpath) {
        return xpath.substring(0, xpath.lastIndexOf("/"));
    }
}

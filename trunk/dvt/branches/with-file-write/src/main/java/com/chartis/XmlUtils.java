package com.chartis;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class XmlUtils {

    public static String parentXpath(String xpath) {
        int lastIndexOf = xpath.lastIndexOf("/");
        if (lastIndexOf == -1) {
            lastIndexOf = xpath.length();
        }
        return xpath.substring(0, lastIndexOf);
    }

    public static String withoutLastIndex(String xpath) {
        String parent = parentXpath(xpath);
        String lastName = lastName(xpath);
        return parent + removeIndex(lastName);
    }

    public static String lastName(String xpath) {
        int start = xpath.lastIndexOf("/");
        if (start == -1) {
            start = 0;
        }
        return xpath.substring(start, xpath.length());
    }

    public static String removeIndex(String xpath) {
        return xpath.replaceAll("\\[\\d*\\]", "");
    }
    
    public static Document stringToDoc(final String xml) throws Exception {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder builder = factory.newDocumentBuilder();
        final InputSource is = new InputSource(new StringReader(xml));
        return builder.parse(is);
    }

}

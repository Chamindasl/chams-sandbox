package com.chartis.dvt.commons.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XmlUtils {

    /**
     * 
     */
    private XmlUtils() {

    }

    public static Document fileAsDocument(final File file) throws ParserConfigurationException, SAXException,
            IOException {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder builder = factory.newDocumentBuilder();
        final InputSource is = new InputSource(new FileReader(file));
        return builder.parse(is);

    }
}

package com.chartis;


import java.io.FileReader;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Ignore;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;


/**
 * 
 * @author CHAMINDA.AMARASINGHE
 *
 */
@Ignore
public class TestData {

   
    private TestData() {

    }
    
    public static Document stringToDoc(final String xml) throws Exception {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder builder = factory.newDocumentBuilder();
        final InputSource is = new InputSource(new StringReader(xml));
        return builder.parse(is);
    }

    public static Document sampleDoc() throws Exception {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder builder = factory.newDocumentBuilder();
        final InputSource is = new InputSource(new FileReader(".//target/test-classes/SamplePolicy.xml"));
        return builder.parse(is);
    }

    public static Document pathToDoc(final String path) throws Exception {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder builder = factory.newDocumentBuilder();
        final InputSource is = new InputSource(new FileReader(path));
        return builder.parse(is);
    }

}

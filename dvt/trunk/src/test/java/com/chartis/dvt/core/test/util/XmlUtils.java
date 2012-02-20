package com.chartis.dvt.core.test.util;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Ignore;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

@Ignore
public class XmlUtils {

    public static String TINY_DOC = "<Policy>" +
    		"<pol_office_cd>182001</pol_office_cd>" +
    		"<policy_no>AG000016</policy_no>" +
    		"<certificate_no>0</certificate_no>" +
    		"<renl_cert_no>0004</renl_cert_no>" +
    		"<eff_dt_seq_no>2000110700</eff_dt_seq_no>" +
    		"<pol_chnge_eff_date>2000-11-07 00:00:00.0</pol_chnge_eff_date>" +
    		"</Policy>"; 

    private XmlUtils() {

    }

    public static Document stringToDoc(final String xml) throws Exception {
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        final DocumentBuilder builder = factory.newDocumentBuilder();
        final InputSource is = new InputSource(new StringReader(xml));
        return builder.parse(is);
    }
    
}

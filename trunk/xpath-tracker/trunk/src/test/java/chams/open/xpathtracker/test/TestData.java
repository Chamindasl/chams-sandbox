package chams.open.xpathtracker.test;

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

    public static String TINY_DOC = "" +
    		"                         <A>" + //1
            "                           <B>182001</B>" + //2
            "                           <C>" + //3
            "                               <D>0</D>" + //4
            "                               <E>" + //5
            "                                   <F>2000110700</F>" + // 6
            "                                   <G>2000110700</G>" + // 7
            "                               </E>" +
            "                           </C>" +
            "                         </A>";

    public static String L3_DOC = "<Policy>" +
            "<pol_office_cd>182001</pol_office_cd>" +
            "<policy_no>AG000016</policy_no>" +
            "<certificate_no>0</certificate_no>" +
            "<renl_cert_no>0004</renl_cert_no>" +
            "<eff_dt_seq_no>2000110700</eff_dt_seq_no>" +
            "<source_system_id>01</source_system_id>" +
            "<record_type_cd>E</record_type_cd>" +
            "<pol_chnge_eff_date>2000-11-07 00:00:00.0</pol_chnge_eff_date>" +
            "<Client>" +
            "<Address><Street>Name of Street</Street></Address>" +
            "</Client>" +
            "</Policy>"; 

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

package com.chartis.dvt.core.test.util;

import java.io.StringWriter;
import java.util.List;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.Diff;
import org.custommonkey.xmlunit.Difference;
import org.custommonkey.xmlunit.DifferenceListener;
import org.custommonkey.xmlunit.IgnoreTextAndAttributeValuesDifferenceListener;
import org.custommonkey.xmlunit.MatchTracker;
import org.custommonkey.xmlunit.NodeDetail;
import org.custommonkey.xmlunit.XMLUnit;
import org.custommonkey.xmlunit.examples.RecursiveElementNameAndTextQualifier;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlUtilTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public final void test() throws Exception {
        XMLUnit.setIgnoreWhitespace(true);
        XMLUnit.setNormalize(true);
        XMLUnit.setNormalizeWhitespace(true);
        Document reference = TestData.pathToDoc(".//target/test-classes/SamplePolicy.xml");
        Document comparison = TestData.pathToDoc(".//target/test-classes/SamplePolicy1.xml");
        Diff diff = new Diff(reference, comparison);
        System.out.println("Similar? " + diff.similar());
        System.out.println("Identical? " + diff.identical());

        DetailedDiff detDiff = new DetailedDiff(diff);

        detDiff.overrideElementQualifier(new RecursiveElementNameAndTextQualifier());
        detDiff.overrideMatchTracker(new MatchTrackerImpl());
//        DifferenceListener myDifferenceListener = new IgnoreTextAndAttributeValuesDifferenceListener();
//        detDiff.overrideDifferenceListener(myDifferenceListener);

        List<?> differences = detDiff.getAllDifferences();
        for (Object object : differences) {
            Difference difference = (Difference) object;
            if (difference != null) {
                if (difference.getControlNodeDetail() != null && difference.getId() != 20) {
                    Node node = difference.getControlNodeDetail().getNode();
                    // if (node != null && node.getNodeType() == Node.TEXT_NODE)
                    // {
                    System.out.println("MisMatch - " + difference.getId() + " - " + difference);
                    pN(node);
                    // }
                }
            }
        }

    }

    private void pN(Node node) {
        if (node != null) {
            System.out.println("     Node Type : " + node.getNodeType());
            System.out.println("     Node Name : " + node.getNodeName());
            System.out.println("     Has Child : " + node.hasChildNodes());
            System.out.println("     Has Child Elements: " + hasChildElements(node));
            Node parentNode = node.getParentNode();
            if (parentNode != null) {
                System.out.println("     Parent Type : " + parentNode.getNodeType());
                System.out.println("     Parent Name: " + parentNode.getNodeName());
                System.out.println("     Parent Has Child : " + parentNode.hasChildNodes());
                System.out.println("     Parent Has Child Elements: " + hasChildElements(parentNode));
            }
        }
    }

    private boolean hasChildElements(Node node) {
        if (node.hasChildNodes()) {
            NodeList nl = node.getChildNodes();
            for (int i = 0; i < nl.getLength(); i++) {
                Node c = nl.item(i);
                if (c.getNodeType() == Node.ELEMENT_NODE) {
                    return true;
                }
            }
        }
        return false;
    }

    class MatchTrackerImpl implements MatchTracker {

        public void matchFound(Difference difference) {
            if (difference != null) {
                if (difference.getControlNodeDetail() != null && difference.getId() == 14) {
                    Node node = difference.getControlNodeDetail().getNode();
                    // if (node != null && node.getNodeType() == Node.TEXT_NODE)
                    // {
                    if (node.getParentNode() != null && !hasChildElements(node.getParentNode())) {
                        System.out.println("Match - " + difference.getId() + " - " + difference);
                        pN(node);
                    }
                    // }
                }
            }
        }

        private String printNode(Node node) {
            if (node != null && node.getNodeType() == Node.ELEMENT_NODE) {
                StringWriter sw = new StringWriter();
                try {
                    Transformer t = TransformerFactory.newInstance().newTransformer();
                    t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
                    t.transform(new DOMSource(node), new StreamResult(sw));
                } catch (TransformerException te) {
                    System.out.println("nodeToString Transformer Exception");
                }
                return sw.toString();

            }
            return null;
        }
    }
}

package com.chartis;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.custommonkey.xmlunit.XpathNodeTracker;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

public class XpathDepthTrackerTest {

    private XpathDepthTracker depthTracker;
    @Before
    public void setUp() throws Exception {
        depthTracker = new XpathDepthTracker();
    }

    @Test
    public final void test() throws Exception {
        Document reference = TestData.pathToDoc(".//target/test-classes/SamplePolicy1.xml");
        depthTracker.traverseNodes(reference, new XpathNodeTracker(), 0);

        Map<Integer, List<ElementDetail>> s = depthTracker.getResultByDepth();
        for (Integer k : s.keySet()) {
            for(ElementDetail e : s.get(k)) {
                System.out.println( " depth  " + k + " element " + e);
            }
        }

        Map<String, List<ElementDetail>> s1 = depthTracker.getResultByXpath();
        for (String k : s1.keySet()) {
            for(ElementDetail e : s1.get(k)) {
                System.out.println( " parent xpath  " + k + " element " + e);
            }
        }
    }

}

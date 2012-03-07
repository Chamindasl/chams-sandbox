package com.chartis;

import static org.junit.Assert.*;

import java.util.Map;

import org.custommonkey.xmlunit.XpathNodeTracker;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

public class MapperTest {
    private Mapper mapper;
    private Document source;
    private Document target;
    private XpathDepthTracker sourceTracker;
    private XpathDepthTracker targetTracker;

    @Before
    public void setUp() throws Exception {
        sourceTracker = new XpathDepthTracker();
        Document reference = TestData.pathToDoc(".//target/test-classes/SamplePolicy1.xml");
        sourceTracker.traverseNodes(reference, new XpathNodeTracker(), 0);

        targetTracker = new XpathDepthTracker();
        Document target = TestData.pathToDoc(".//target/test-classes/SamplePolicy2.xml");
        targetTracker.traverseNodes(target, new XpathNodeTracker(), 0);

        mapper = new Mapper(null, null, sourceTracker, targetTracker);
        
        
    }

    @Test
    public final void test() {
        mapper.map();
        Map<String, String> sToT = mapper.getSourceToTarget();
        
        for (String s : sToT.keySet()) {
            System.out.println( " s " + s + " to t " + sToT.get(s));
        }
    }

}

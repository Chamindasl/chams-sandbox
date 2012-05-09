package chams.open.xpathtracker.engine;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


import chams.open.xpathtracker.event.ListHolderListener;
import chams.open.xpathtracker.event.PrintListener;
import chams.open.xpathtracker.event.XPathFoundEvent;
import chams.open.xpathtracker.event.XPathFoundListener;
import chams.open.xpathtracker.test.TestData;

public class XPathFinderTest {

    private XPathFinder finder;
    private List<XPathFoundListener> foundListeners;
    private ListHolderListener listHolderListener;

    @Before
    public void setUp() throws Exception {
        finder = new XPathFinder();
        foundListeners = new ArrayList<XPathFoundListener>();
        listHolderListener = new ListHolderListener();
        foundListeners.add(new PrintListener());
        foundListeners.add(listHolderListener);
        finder.setListeners(foundListeners);
    }

    @Test
    public void testTINY_DOC() throws Exception {
        finder.find(TestData.stringToDoc(TestData.TINY_DOC));
        assertEquals(7, listHolderListener.getXPathFoundEvents().size() );
        
        final XPathFoundEvent e = listHolderListener.getXPathFoundEvents().get(0);
        assertEquals("/A[1]", e.getXpath());
        assertEquals(2, e.getNoOfChildElements());
        assertEquals(1, e.getNoOfTextChildElements());
        assertEquals(1, e.getNoOfComplexChildElements());
    }


    @Test
    public void testLargeDocVisit() throws Exception {
        finder.find(TestData.pathToDoc(".//target/test-classes/SamplePolicy1.xml"));
        assertTrue(listHolderListener.getXPathFoundEvents().size() > 0);
    }

}

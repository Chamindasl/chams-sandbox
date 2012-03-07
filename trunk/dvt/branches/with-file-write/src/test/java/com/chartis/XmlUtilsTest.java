package com.chartis;

import static org.junit.Assert.*;
import static com.chartis.XmlUtils.*;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XmlUtilsTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public final void testParentXpath() {
        assertEquals("//a[0]/b[1]/c[2]", parentXpath("//a[0]/b[1]/c[2]/d"));
        assertEquals("//a[0]/b[1]/c[2]", parentXpath("//a[0]/b[1]/c[2]/d[0]"));
        assertEquals("//a/b/c", parentXpath("//a/b/c/d"));
    }

    @Test
    public final void testWithoutLastIndex() {
        assertEquals("//a[0]/b[1]/c", withoutLastIndex("//a[0]/b[1]/c[2]"));
        assertEquals("//a[0]/b[1]/c", withoutLastIndex("//a[0]/b[1]/c"));
        assertEquals("//a/b/c", withoutLastIndex("//a/b/c"));
    }

    @Test
    public final void testLastName() {
        assertEquals("/c[2]", XmlUtils.lastName("//a[0]/b[1]/c[2]"));
        assertEquals("/c", XmlUtils.lastName("//a[0]/b[1]/c"));
    }

    @Test
    public final void testStringToDoc() throws Exception {
    }

}

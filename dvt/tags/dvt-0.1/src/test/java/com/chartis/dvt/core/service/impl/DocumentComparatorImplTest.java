package com.chartis.dvt.core.service.impl;

import java.sql.SQLException;

import javax.xml.xpath.XPathExpressionException;

import org.junit.Before;
import org.junit.Test;

import com.chartis.dvt.core.test.util.TestData;

public class DocumentComparatorImplTest {

    private DocumentComparatorImpl documentComparatorImpl;

    @Before
    public void setUp() throws Exception {
        documentComparatorImpl = new DocumentComparatorImpl();
    }

    @Test
    public final void testCompare() throws XPathExpressionException, SQLException, Exception {
        documentComparatorImpl.compare(TestData.sampleDoc(), "SamplePolicy.xml");
    }

}

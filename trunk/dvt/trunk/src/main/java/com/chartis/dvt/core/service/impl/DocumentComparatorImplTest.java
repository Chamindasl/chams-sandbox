package com.chartis.dvt.core.service.impl;

import static org.junit.Assert.*;

import java.sql.SQLException;

import javax.xml.xpath.XPathExpressionException;

import org.junit.Before;
import org.junit.Test;

import com.chartis.dvt.core.test.util.TestData;

public class DocumentComparatorImplTest {

    private DocumentComparatorImpl comparatorImpl;

    @Before
    public void setUp() throws Exception {
        comparatorImpl = new DocumentComparatorImpl();
    }

    @Test
    public final void testCompare() throws XPathExpressionException, SQLException, Exception {
        comparatorImpl.compare(TestData.stringToDoc(TestData.L3_DOC));
    }

}

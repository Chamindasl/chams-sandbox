package com.chartis.dvt.core.xml.model;

import javax.xml.xpath.XPathExpressionException;

import junit.framework.Assert;

import org.junit.Test;

import com.chartis.dvt.core.test.util.XmlUtils;

public class ActivePolicyXmlWrapperTest {

    @Test(expected = AssertionError.class)
    public void testWithNull() throws XPathExpressionException {
        new ActivePolicyXmlWrapper(null);
    }

    @Test()
    public void testLoadKeys() throws Exception{
        final ActivePolicyXmlWrapper wrapper = new ActivePolicyXmlWrapper(XmlUtils.stringToDoc(XmlUtils.TINY_DOC));
        Assert.assertEquals("182001", wrapper.getPolOfficeCd());
        Assert.assertEquals("AG000016", wrapper.getPolicy_No());
        Assert.assertEquals(0, wrapper.getCertificateNo().intValue());
    }

}

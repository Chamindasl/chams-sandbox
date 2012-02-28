package com.chartis.dvt.core.xml.model;

import javax.xml.xpath.XPathExpressionException;

import static junit.framework.Assert.*;

import org.junit.Test;

import com.chartis.dvt.core.model.xml.ActivePolicyXmlWrapper;
import com.chartis.dvt.core.test.util.TestData;

/**
 * 
 * @author CHAMINDA.AMARASINGHE
 *
 */
public class ActivePolicyXmlWrapperTest {

    @Test(expected = AssertionError.class)
    public void testWithNull() throws XPathExpressionException {
        new ActivePolicyXmlWrapper(null);
    }

    @Test()
    public void testLoadKeys() throws Exception{
        final ActivePolicyXmlWrapper wrapper = new ActivePolicyXmlWrapper(TestData.stringToDoc(TestData.TINY_DOC));
        assertEquals("182001", wrapper.getPolicyKeys().getPolOfficeCd());
        assertEquals("AG000016", wrapper.getPolicyKeys().getPolicyNo());
        assertEquals(0, wrapper.getPolicyKeys().getCertificateNo().intValue());
    }

    @Test()
    public void getValue() throws Exception{
        final ActivePolicyXmlWrapper wrapper = new ActivePolicyXmlWrapper(TestData.stringToDoc(TestData.L3_DOC));
        assertEquals("Name of Street", wrapper.getValue("/Policy/Client/Address/Street"));
        
    }

}

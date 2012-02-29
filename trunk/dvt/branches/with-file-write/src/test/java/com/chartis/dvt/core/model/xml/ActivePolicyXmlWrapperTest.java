package com.chartis.dvt.core.model.xml;

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
    public void testGetValue() throws Exception{
        final ActivePolicyXmlWrapper wrapper = new ActivePolicyXmlWrapper(TestData.stringToDoc(TestData.L3_DOC));
        assertEquals("Name of Street", wrapper.getValue("/Policy/Client/Address/Street"));
        
    }

    @Test()
    public void testNotExistingGet() throws Exception{
        final ActivePolicyXmlWrapper wrapper = new ActivePolicyXmlWrapper(TestData.stringToDoc(TestData.L3_DOC));
        assertEquals("", wrapper.getValue("/Policy/Client/Address/Street/NOT_EXIST"));
        
    }

    @Test()
    public void testLoadOtherKeys() throws Exception{
        final ActivePolicyXmlWrapper wrapper = new ActivePolicyXmlWrapper(TestData.stringToDoc(TestData.L3_DOC));
        assertEquals("01", wrapper.getOtherKeys().get(ActivePolicyXmlWrapper.SOURCE_SYSTEM_ID));
        assertEquals("E", wrapper.getOtherKeys().get(ActivePolicyXmlWrapper.RECORD_TYPE_CD));
        
    }

}

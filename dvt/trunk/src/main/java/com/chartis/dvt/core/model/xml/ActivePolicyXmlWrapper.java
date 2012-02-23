package com.chartis.dvt.core.model.xml;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.chartis.dvt.core.model.PolicyKeys;
import com.chartis.dvt.core.model.exception.DvtException;

import static com.chartis.dvt.commons.utils.Assert.*;
import static com.chartis.dvt.commons.utils.StringUtils.*;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;

public class ActivePolicyXmlWrapper {

    private Document document;
    private XPath xpath;
    private PolicyKeys policyKeys;
    private Map<String, String> otherKeys; 

    public Map<String, String> getOtherKeys() {
        return otherKeys;
    }

    public PolicyKeys getPolicyKeys() {
        return policyKeys;
    }

    public ActivePolicyXmlWrapper(final Document document) throws XPathExpressionException {
        notNull(document);
        this.document = document;
        xpath = XPathFactory.newInstance().newXPath();
        loadPolicyKeys();
        if (!validatePolicyKeys()) {
            throw new DvtException("Could not fetch the policy keys from the xml");
        }
        loadOtherKeys();
    }

    private void loadPolicyKeys() throws XPathExpressionException {
        policyKeys = new PolicyKeys();
        policyKeys.setPolOfficeCd((String) xpath.compile("//pol_office_cd").evaluate(document, XPathConstants.STRING));
        policyKeys.setPolicyNo((String) xpath.compile("//policy_no").evaluate(document, XPathConstants.STRING));
        policyKeys.setCertificateNo(((Double) xpath.compile("//certificate_no").evaluate(document, XPathConstants.NUMBER)).intValue());
        policyKeys.setRenlCertNo((String) xpath.compile("//renl_cert_no").evaluate(document, XPathConstants.STRING));
        policyKeys.setEffDtSeqNo(((Double) xpath.compile("//eff_dt_seq_no").evaluate(document, XPathConstants.NUMBER)).intValue());
    }
    
    public String getValue(final String xpathName) throws XPathExpressionException {
        final Object result = xpath.compile(xpathName).evaluate(document, XPathConstants.STRING);
        if (result == null) {
            return null;
        } else {
            return result.toString();
        }
    }
    
    private void loadOtherKeys() throws XPathExpressionException {
        final Map <String, String> temp = new HashMap<String, String>();
        temp.put("SourceSystemId", ((String) xpath.compile("//source_system_id").evaluate(document, XPathConstants.STRING)));
        otherKeys = Collections.unmodifiableMap(temp);
    }
    
    private boolean validatePolicyKeys() {
        return allHasText(policyKeys.getPolicyNo(),policyKeys.getPolOfficeCd(), policyKeys.getRenlCertNo())
                && policyKeys.getCertificateNo() != null 
                && policyKeys.getEffDtSeqNo() != null && policyKeys.getEffDtSeqNo() > 0;
    }
}

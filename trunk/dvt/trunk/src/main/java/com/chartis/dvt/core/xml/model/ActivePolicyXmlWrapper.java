package com.chartis.dvt.core.xml.model;

import static com.chartis.dvt.core.util.Assert.*;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;

public class ActivePolicyXmlWrapper {

    private Document document;
    private XPath xpath;
    private String polOfficeCd;
    private String policy_No;
    private Integer certificateNo;
    private String renlCertNo;
    private String effDtSeqNo;

    public String getPolOfficeCd() {
        return polOfficeCd;
    }

    public Integer getCertificateNo() {
        return certificateNo;
    }

    public String getPolicy_No() {
        return policy_No;
    }

    public String getRenlCertNo() {
        return renlCertNo;
    }

    public String getEffDtSeqNo() {
        return effDtSeqNo;
    }

    public ActivePolicyXmlWrapper(final Document document) throws XPathExpressionException {
        notNull(document);
        this.document = document;
        xpath = XPathFactory.newInstance().newXPath();
        loadPolicyKeys();
    }

    private void loadPolicyKeys() throws XPathExpressionException {
        polOfficeCd = (String) xpath.compile("//pol_office_cd").evaluate(document, XPathConstants.STRING);
        policy_No = (String) xpath.compile("//policy_no").evaluate(document, XPathConstants.STRING);
        certificateNo = ((Double) xpath.compile("//certificate_no").evaluate(document, XPathConstants.NUMBER))
                .intValue();
        renlCertNo = (String) xpath.compile("//renl_cert_no").evaluate(document, XPathConstants.STRING);
        effDtSeqNo = (String) xpath.compile("//eff_dt_seq_no").evaluate(document, XPathConstants.STRING);
    }

}

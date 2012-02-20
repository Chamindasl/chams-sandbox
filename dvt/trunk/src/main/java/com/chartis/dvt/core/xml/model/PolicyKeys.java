/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chartis.dvt.core.xml.model;

/**
 *
 * @author chams
 */
public class PolicyKeys {

    private String polOfficeCd;
    private String policy_No;
    private Integer certificateNo;
    private String renlCertNo;
    private String effDtSeqNo;

    public void setCertificateNo(Integer certificateNo) {
        this.certificateNo = certificateNo;
    }

    public void setEffDtSeqNo(String effDtSeqNo) {
        this.effDtSeqNo = effDtSeqNo;
    }

    public void setPolOfficeCd(String polOfficeCd) {
        this.polOfficeCd = polOfficeCd;
    }

    public void setPolicy_No(String policy_No) {
        this.policy_No = policy_No;
    }

    public void setRenlCertNo(String renlCertNo) {
        this.renlCertNo = renlCertNo;
    }


    public Integer getCertificateNo() {
        return certificateNo;
    }

    public String getEffDtSeqNo() {
        return effDtSeqNo;
    }

    public String getPolOfficeCd() {
        return polOfficeCd;
    }

    public String getPolicy_No() {
        return policy_No;
    }

    public String getRenlCertNo() {
        return renlCertNo;
    }
    
}

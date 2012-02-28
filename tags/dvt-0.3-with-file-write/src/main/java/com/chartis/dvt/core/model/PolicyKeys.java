package com.chartis.dvt.core.model;

/**
 * 
 * @author CHAMINDA.AMARASINGHE
 *
 */
public class PolicyKeys {
    
    private String polOfficeCd;
    private String policyNo;
    private Integer certificateNo;
    private String renlCertNo;
    private Integer effDtSeqNo;

    public void setCertificateNo(Integer certificateNo) {
        this.certificateNo = certificateNo;
    }

    public void setEffDtSeqNo(Integer effDtSeqNo) {
        this.effDtSeqNo = effDtSeqNo;
    }

    public void setPolOfficeCd(String polOfficeCd) {
        this.polOfficeCd = polOfficeCd;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public void setRenlCertNo(String renlCertNo) {
        this.renlCertNo = renlCertNo;
    }


    public Integer getCertificateNo() {
        return certificateNo;
    }

    public Integer getEffDtSeqNo() {
        return effDtSeqNo;
    }

    public String getPolOfficeCd() {
        return polOfficeCd;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public String getRenlCertNo() {
        return renlCertNo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PolicyKeys [polOfficeCd=");
        sb.append(polOfficeCd);
        sb.append(", policyNo=");
        sb.append(policyNo);
        sb.append(", certificateNo=");
        sb.append(certificateNo);
        sb.append(", renlCertNo=");
        sb.append(renlCertNo);
        sb.append(", effDtSeqNo=");
        sb.append(effDtSeqNo);
        sb.append("]");
        return sb.toString();
    }
    
}

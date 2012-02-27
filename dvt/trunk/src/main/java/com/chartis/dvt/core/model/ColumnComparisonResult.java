package com.chartis.dvt.core.model;

import com.chartis.dvt.core.db.model.DvtColumn.EvaluationCode;

/**
 * 
 * @author CHAMINDA.AMARASINGHE
 *
 */
public class ColumnComparisonResult {

    private String dbValue;
    private String dbColumn;
    private String xmlValue;
    private String xmlElement;
    private EvaluationCode evaluationCode;
    private boolean match;

    public String getDbValue() {
        return dbValue;
    }
    public void setDbValue(String dbValue) {
        this.dbValue = dbValue;
    }
    public String getDbColumn() {
        return dbColumn;
    }
    public void setDbColumn(String dbColumn) {
        this.dbColumn = dbColumn;
    }
    public String getXmlValue() {
        return xmlValue;
    }
    public void setXmlValue(String xmlValue) {
        this.xmlValue = xmlValue;
    }
    public String getXmlElement() {
        return xmlElement;
    }
    public void setXmlElement(String xmlElement) {
        this.xmlElement = xmlElement;
    }
    public EvaluationCode getEvaluationCode() {
        return evaluationCode;
    }
    public void setEvaluationCode(EvaluationCode evaluationCode) {
        this.evaluationCode = evaluationCode;
    }
    public boolean isMatch() {
        return match;
    }
    public void setMatch(boolean match) {
        this.match = match;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ComparisonResult [dbValue=");
        sb.append(dbValue);
        sb.append(", xmlValue=");
        sb.append(xmlValue);
        sb.append(", dbColumn=");
        sb.append(dbColumn);
        sb.append(", xmlElement=");
        sb.append(xmlElement);
        sb.append(", evaluationCode=");
        sb.append(evaluationCode);
        sb.append(", match=");
        sb.append(match);
        sb.append("]");
        return sb.toString();
    }

}

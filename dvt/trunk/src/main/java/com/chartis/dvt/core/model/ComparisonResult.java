package com.chartis.dvt.core.model;

import com.chartis.dvt.core.db.model.DvtColumn.EvaluationCode;

public class ComparisonResult {

    private String db;
    private String xml;
    private EvaluationCode evaluationCode;
    private boolean match;

    public String getDb() {
        return db;
    }
    public void setDb(String db) {
        this.db = db;
    }
    public String getXml() {
        return xml;
    }
    public void setXml(String xml) {
        this.xml = xml;
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
        return "ComparisonResult [db=" + db + ", xml=" + xml + ", evaluationCode=" + evaluationCode + ", match="
                + match + "]";
    }
    
}

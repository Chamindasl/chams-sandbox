package com.chartis.dvt.core.model;

import java.util.Date;

public class DocumentComparisonResult {

    private String docName;
    private int exactMatch;
    private int misMatch;
    private int niglect;
    private Date time;
    
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public int getExactMatch() {
        return exactMatch;
    }

    public void increaseExactMatch() {
        ++exactMatch;
    }

    public int getMisMatch() {
        return misMatch;
    }

    public void increaseMisMatch() {
        ++misMatch;
    }

    public int getNiglect() {
        return niglect;
    }

    public void increaseNiglect() {
        ++niglect;
    }
    
}

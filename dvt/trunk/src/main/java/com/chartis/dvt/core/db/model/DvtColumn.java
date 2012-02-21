package com.chartis.dvt.core.db.model;

public class DvtColumn {
    
    private DvtTable dvtTable;
    private String name;
    private DataType dataType;
    private String xmlElement;
    private String xpath;
    private EvaluationCode evaluationCode;

    public DvtTable getDvtTable() {
        return dvtTable;
    }

    public void setDvtTable(DvtTable dvtTable) {
        this.dvtTable = dvtTable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataType getDataType() {
        return dataType;
    }

    public void setDataType(DataType dataType) {
        this.dataType = dataType;
    }

    public String getXmlElement() {
        return xmlElement;
    }

    public void setXmlElement(String xmlElement) {
        this.xmlElement = xmlElement;
    }

    public String getXpath() {
        return xpath;
    }

    public void setXpath(String xpath) {
        this.xpath = xpath;
    }

    public EvaluationCode getEvaluationCode() {
        return evaluationCode;
    }

    public void setEvaluationCode(EvaluationCode evaluationCode) {
        this.evaluationCode = evaluationCode;
    }

    public static enum DataType {
        VARCHAR, INTEGER, DOUBLE, DATE;
    }

    public static enum EvaluationCode {
        EXACT, ENRICHED, NO_COMPARE;

        public static EvaluationCode get(int code) {
            switch (code) {
            case 1:
                return EXACT;
            case 2:
                return ENRICHED;
            case 3:
                return NO_COMPARE;
            default:
                return NO_COMPARE;
            }
        }
    }

}

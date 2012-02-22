package com.chartis.dvt.core.service.impl;

import java.util.Map;
import java.util.logging.Logger;

import javax.xml.xpath.XPathExpressionException;

import static com.chartis.dvt.commons.utils.StringUtils.*;
import com.chartis.dvt.core.db.model.DvtColumn;
import com.chartis.dvt.core.db.model.DvtColumn.EvaluationCode;
import com.chartis.dvt.core.model.ComparisonResult;
import com.chartis.dvt.core.service.ColumnComparator;
import com.chartis.dvt.core.xml.model.ActivePolicyXmlWrapper;


public class ColumnComparatorImpl implements ColumnComparator{
    
    private static Logger logger = Logger.getLogger(ColumnComparatorImpl.class .getName());

    public ComparisonResult compare(final DvtColumn column, final ActivePolicyXmlWrapper policyXmlWrapper,
            final Map<String, Object> dbResult) throws XPathExpressionException {
        final String xmlValue = policyXmlWrapper.getValue(catAsIs("/",
                column.getXpath(), "/", column.getXmlElement()));
        final Object dbOjb = dbResult.get(column.getName());
        final String dbValue;
        if (dbOjb instanceof byte[]) {
            dbValue = convertByteArrayToString(dbOjb);
        } else {
            dbValue = dbOjb.toString();
        }
        logger.info(cat("Object type is ", dbOjb.getClass(), "is byte array? ", (dbOjb instanceof byte[]), ", converted to string as ", dbValue));
        final ComparisonResult result = new ComparisonResult();
        result.setDbColumn(cat(false, column.getDvtTable().getName(), ".", column.getName()));
        result.setDbValue(dbValue);
        result.setXmlElement(cat(false, column.getXpath(), "/", column.getXmlElement()));
        result.setXmlValue(xmlValue);
        result.setEvaluationCode(column.getEvaluationCode());
        if (column.getEvaluationCode() == EvaluationCode.EXACT) {
            result.setMatch(dbValue.trim().equals(xmlValue.trim()));
        }
        return result;
    }

    private String convertByteArrayToString(final Object dbOjb) {
        final String dbValue;
        final String temp = new String((byte[])dbOjb);
        if (temp != null & temp.length() == 1) {
            final int sAsI = temp.charAt(0);
            dbValue = Integer.toHexString(sAsI);
        } else {
            dbValue = temp;
        }
        return dbValue;
    }

}

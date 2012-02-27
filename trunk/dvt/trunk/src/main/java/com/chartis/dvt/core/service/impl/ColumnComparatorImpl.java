package com.chartis.dvt.core.service.impl;

import java.util.Map;
import java.util.logging.Logger;

import javax.xml.xpath.XPathExpressionException;

import static com.chartis.dvt.commons.utils.StringUtils.*;
import com.chartis.dvt.core.db.model.DvtColumn;
import com.chartis.dvt.core.db.model.DvtColumn.EvaluationCode;
import com.chartis.dvt.core.model.ColumnComparisonResult;
import com.chartis.dvt.core.model.xml.ActivePolicyXmlWrapper;
import com.chartis.dvt.core.service.ColumnComparator;

/**
 * 
 * @author CHAMINDA.AMARASINGHE
 *
 */
public class ColumnComparatorImpl implements ColumnComparator{
    
    private static Logger logger = Logger.getLogger(ColumnComparatorImpl.class .getName());

    public ColumnComparisonResult compare(final DvtColumn column, final ActivePolicyXmlWrapper policyXmlWrapper,
            final Map<String, Object> dbResult) throws XPathExpressionException {
        final String xmlValue = policyXmlWrapper.getValue(catAsIs("/",
                column.getXpath(), "/", column.getXmlElement()));
        final Object dbOjb = dbResult.get(column.getName());
        final String dbValue;
        if (dbOjb instanceof byte[]) {
            dbValue = convertByteArrayToString(dbOjb);
            logger.info(cat("Found byte array, Converted as ", dbValue));
        } else {
            dbValue = dbOjb.toString();
        }
        final ColumnComparisonResult result = new ColumnComparisonResult();
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

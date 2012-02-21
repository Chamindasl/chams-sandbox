package com.chartis.dvt.core.service.impl;

import java.util.Map;

import javax.xml.xpath.XPathExpressionException;

import static com.chartis.dvt.commons.utils.StringUtils.*;
import com.chartis.dvt.core.db.model.DvtColumn;
import com.chartis.dvt.core.db.model.DvtColumn.EvaluationCode;
import com.chartis.dvt.core.model.ComparisonResult;
import com.chartis.dvt.core.service.ColumnComparator;
import com.chartis.dvt.core.xml.model.ActivePolicyXmlWrapper;

public class ColumnComparatorImpl implements ColumnComparator{

    public ComparisonResult compare(final DvtColumn column, final ActivePolicyXmlWrapper policyXmlWrapper,
            final Map<String, Object> dbResult) throws XPathExpressionException {
        final String xmlValue = policyXmlWrapper.getValue(catAsIs("/",
                column.getXpath(), "/", column.getXmlElement()));
        
        final String dbValue = dbResult.get(column.getName()).toString();
        final ComparisonResult result = new ComparisonResult();
        result.setDbColumn(cat(false, column.getDvtTable().getName(), ".", column.getName()));
        result.setDbValue(dbValue);
        result.setXmlElement(cat(false, column.getXpath(), "/", column.getXmlElement()));
        result.setXmlValue(xmlValue);
        result.setEvaluationCode(column.getEvaluationCode());
        if (column.getEvaluationCode() == EvaluationCode.EXACT) {
            result.setMatch(dbValue.equals(xmlValue));
        }
        return result;
    }

}

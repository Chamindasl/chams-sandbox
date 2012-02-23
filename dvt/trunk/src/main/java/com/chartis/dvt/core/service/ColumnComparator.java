package com.chartis.dvt.core.service;

import java.util.Map;

import javax.xml.xpath.XPathExpressionException;

import com.chartis.dvt.core.db.model.DvtColumn;
import com.chartis.dvt.core.model.ColumnComparisonResult;
import com.chartis.dvt.core.model.xml.ActivePolicyXmlWrapper;

public interface ColumnComparator {

    ColumnComparisonResult compare(final DvtColumn column, final ActivePolicyXmlWrapper policyXmlWrapper,
            final Map<String, Object> dbValue) throws XPathExpressionException;
}

package com.chartis.dvt.core.service;

import java.sql.SQLException;

import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;

import com.chartis.dvt.core.model.DocumentComparisonResult;

/**
 * 
 * @author CHAMINDA.AMARASINGHE
 *
 */
public interface DocumentComparator {
    DocumentComparisonResult compare(final Document document, final String docName)
            throws XPathExpressionException, SQLException;
}

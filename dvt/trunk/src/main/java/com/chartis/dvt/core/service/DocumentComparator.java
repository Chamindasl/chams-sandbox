package com.chartis.dvt.core.service;

import java.sql.SQLException;

import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;

public interface DocumentComparator {
    void compare(final Document document, final String docName)
            throws XPathExpressionException, SQLException;
}

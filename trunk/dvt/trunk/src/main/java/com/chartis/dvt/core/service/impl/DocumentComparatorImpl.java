package com.chartis.dvt.core.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.xml.xpath.XPathExpressionException;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.w3c.dom.Document;
import com.chartis.dvt.core.xml.model.ActivePolicyXmlWrapper;
import com.chartis.dvt.core.dao.DvtColumnDao;
import com.chartis.dvt.core.dao.GoldDao;
import com.chartis.dvt.core.dao.impl.DvtColumnDaoImpl;
import com.chartis.dvt.core.dao.impl.GoldDaoImpl;
import com.chartis.dvt.core.db.model.DvtColumn;
import com.chartis.dvt.core.model.ComparisonResult;
import com.chartis.dvt.core.model.LineOfBusiness;
import com.chartis.dvt.core.model.PolicyKeys;
import com.chartis.dvt.core.service.ColumnComparator;
import com.chartis.dvt.core.service.DocumentComparator;
import com.chartis.dvt.jdbc.SimpleDataSourceProvider;

public class DocumentComparatorImpl implements DocumentComparator{

    private GoldDao goldDao;
    private DvtColumnDao dvtColumnDao;

    public DocumentComparatorImpl () {
        
        // this is ugly. we need a service locater 
        // For the now will do this.
        final GoldDaoImpl goldDaoImpl = new GoldDaoImpl();
        final DataSource dataSource = new SimpleDataSourceProvider().getDataSource();
        goldDaoImpl.setDataSource(dataSource);
        goldDao = goldDaoImpl;
        
        final DvtColumnDaoImpl dvtColumnDaoImpl = new DvtColumnDaoImpl();
        dvtColumnDaoImpl.setDataSource(dataSource);
        dvtColumnDao = dvtColumnDaoImpl;
    }

    public void compare(final Document document) throws XPathExpressionException, SQLException {
        
        // create ActivePolicyXmlWrapper
        // get major line
        // load tables
        // load record
        final ActivePolicyXmlWrapper activePolicyXmlWrapper = new ActivePolicyXmlWrapper(document);
        PolicyKeys policyKeys = activePolicyXmlWrapper.getPolicyKeys();
        final int majorCode = goldDao.getMajorLine(policyKeys);
        final List<DvtColumn> columns = dvtColumnDao.findAllByLob(LineOfBusiness.COMMON);
        LineOfBusiness lobbyCode = LineOfBusiness.byCode(majorCode);
        if (lobbyCode != LineOfBusiness.COMMON) {
            columns.addAll(dvtColumnDao.findAllByLob(lobbyCode));
        }
        String tableName = "";
        Map <String, Object> dbResult = null;
        for (DvtColumn column : columns) {
            if (!tableName.equalsIgnoreCase(column.getDvtTable().getName())) {
                tableName = column.getDvtTable().getName();
                dbResult = goldDao.getRecordAsMap(tableName, policyKeys);
            }
            
            final ColumnComparator columnComparator = new ColumnComparatorImpl();
            System.out.println(columnComparator.compare(column, activePolicyXmlWrapper, dbResult));
        }
    }
}

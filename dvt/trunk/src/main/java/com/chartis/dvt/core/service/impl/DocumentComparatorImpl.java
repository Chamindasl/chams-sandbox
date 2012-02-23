package com.chartis.dvt.core.service.impl;

import static com.chartis.dvt.commons.utils.StringUtils.cat;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.xml.xpath.XPathExpressionException;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.w3c.dom.Document;

import com.chartis.dvt.core.dao.DvtColumnDao;
import com.chartis.dvt.core.dao.DvtLogDao;
import com.chartis.dvt.core.dao.GoldDao;
import com.chartis.dvt.core.dao.impl.DvtColumnDaoImpl;
import com.chartis.dvt.core.dao.impl.DvtLogDaoImpl;
import com.chartis.dvt.core.dao.impl.GoldDaoImpl;
import com.chartis.dvt.core.db.model.DvtColumn;
import com.chartis.dvt.core.db.model.DvtColumn.EvaluationCode;
import com.chartis.dvt.core.db.model.DvtLog;
import com.chartis.dvt.core.model.ColumnComparisonResult;
import com.chartis.dvt.core.model.DocumentComparisonResult;
import com.chartis.dvt.core.model.LineOfBusiness;
import com.chartis.dvt.core.model.PolicyKeys;
import com.chartis.dvt.core.service.ColumnComparator;
import com.chartis.dvt.core.service.DocumentComparator;
import com.chartis.dvt.core.xml.model.ActivePolicyXmlWrapper;
import com.chartis.dvt.jdbc.SimpleDataSourceProvider;

public class DocumentComparatorImpl implements DocumentComparator{

    private static Logger logger = Logger.getLogger(DocumentComparatorImpl.class .getName());

    private GoldDao goldDao;
    private DvtColumnDao dvtColumnDao;
    private DvtLogDao dvtLogDao;

    public DocumentComparatorImpl () {
        lookupServices();
    }

    private void lookupServices() {
        // this is ugly. we need a service locater 
        // For the now will do this.
        final GoldDaoImpl goldDaoImpl = new GoldDaoImpl();
        final DataSource dataSource = new SimpleDataSourceProvider().getDataSource();
        goldDaoImpl.setDataSource(dataSource);
        goldDao = goldDaoImpl;
        
        final DvtColumnDaoImpl dvtColumnDaoImpl = new DvtColumnDaoImpl();
        dvtColumnDaoImpl.setDataSource(dataSource);
        dvtColumnDao = dvtColumnDaoImpl;

        final DvtLogDaoImpl dvtLogDaoImpl = new DvtLogDaoImpl();
        dvtLogDaoImpl.setDataSource(dataSource);
        dvtLogDao = dvtLogDaoImpl;
    }

    public DocumentComparisonResult compare(final Document document, final String docName) throws XPathExpressionException, SQLException {
        
        final ActivePolicyXmlWrapper wrapper = new ActivePolicyXmlWrapper(document);
        final PolicyKeys policyKeys = wrapper.getPolicyKeys();
        final int majorCode = goldDao.getMajorLine(policyKeys);
        final List<DvtColumn> columns = dvtColumnDao.findAllByLob(LineOfBusiness.COMMON);
        final LineOfBusiness lobbyCode = LineOfBusiness.byCode(majorCode);
        logger.info(cat("Fetched Plicy keys", policyKeys));
        logger.info(cat("Fetched LineOfBusiness", lobbyCode));
        final Date now = new Date();
        final DocumentComparisonResult documentCompariosonResult = new DocumentComparisonResult();
        documentCompariosonResult.setTime(now);
        documentCompariosonResult.setDocName(docName);
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
            
            final ColumnComparisonResult comparisonResult = getComparisonResult(wrapper, dbResult, column);
            updateDocumentComparisonResult(documentCompariosonResult, comparisonResult);
            saveLog(docName, wrapper, column, comparisonResult, now);
        }
        return documentCompariosonResult;
    }

    private void updateDocumentComparisonResult(final DocumentComparisonResult documentCompariosonResult,
            final ColumnComparisonResult comparisonResult) {
        if (comparisonResult.getEvaluationCode()== EvaluationCode.EXACT) {
            if (comparisonResult.isMatch()) {
                documentCompariosonResult.increaseExactMatch();
            } else {
                documentCompariosonResult.increaseMisMatch();
            }
        } else {
            documentCompariosonResult.increaseNiglect();
        }
    }

    private void saveLog(final String docName, final ActivePolicyXmlWrapper wrapper, DvtColumn column,
            final ColumnComparisonResult comparisonResult, final Date date) throws SQLException {
        final DvtLog dvtLog = buildDvtLog(wrapper, column, comparisonResult, docName);
        dvtLog.setTimestamp(date);
        dvtLogDao.save(dvtLog);
    }

    private ColumnComparisonResult getComparisonResult(final ActivePolicyXmlWrapper wrapper, Map<String, Object> dbResult,
            DvtColumn column) throws XPathExpressionException {
        final ColumnComparator columnComparator = new ColumnComparatorImpl();
        final ColumnComparisonResult comparisonResult = columnComparator.compare(column, wrapper, dbResult);
        return comparisonResult;
    }

    private DvtLog buildDvtLog(final ActivePolicyXmlWrapper wrapper, final DvtColumn column,
            final ColumnComparisonResult comparisonResult, final String file) {
        final DvtLog dvtLog = new DvtLog();
        DvtLogBuilder.fillFrom(wrapper, dvtLog);
        DvtLogBuilder.fillFrom(column, dvtLog);
        DvtLogBuilder.fillFrom(comparisonResult, dvtLog);
        dvtLog.setXmlFileName(file);
        return dvtLog;
    }
    
    private static class DvtLogBuilder {

        static void fillFrom( final ActivePolicyXmlWrapper wrapper, final DvtLog dvtLog) {
            dvtLog.setSourceSystemId(wrapper.getOtherKeys().get("SourceSystemId"));
            dvtLog.setCertificateNo(wrapper.getPolicyKeys().getCertificateNo());
            dvtLog.setEffDtSeq_no(wrapper.getPolicyKeys().getEffDtSeqNo());
            dvtLog.setPolicyNo(wrapper.getPolicyKeys().getPolicyNo());
            dvtLog.setPolOfficeCd(wrapper.getPolicyKeys().getPolOfficeCd());
            dvtLog.setRenlCertNo(wrapper.getPolicyKeys().getRenlCertNo());
            //TODO
            dvtLog.setTransTypeDesc("New Business");
        }
        
        static void fillFrom(final ColumnComparisonResult comparisonResult, final DvtLog dvtLog) {
            dvtLog.setColValue(comparisonResult.getDbValue());
            dvtLog.setXmlElementValue(comparisonResult.getXmlValue());

            // TODO
            if (comparisonResult.isMatch()) {
                dvtLog.setStatusCd("01");
            } else {
                dvtLog.setStatusCd("02");
            }

        }
        
        static void fillFrom(final DvtColumn column, final DvtLog dvtLog) {
            dvtLog.setTableName(column.getDvtTable().getName());
            dvtLog.setColName(column.getName());
            dvtLog.setEvalCd(Integer.toString(column.getEvaluationCode().getCode()));
            dvtLog.setDomainName(column.getDomainName());
            dvtLog.setLobName(column.getDvtTable().getLineOfBusiness());
            dvtLog.setColOrder(column.getColumnOrder());
        }
    }
}

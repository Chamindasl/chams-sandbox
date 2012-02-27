package com.chartis.dvt.core.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.chartis.dvt.core.dao.AbstractDao;
import com.chartis.dvt.core.dao.DvtLogDao;
import com.chartis.dvt.core.db.model.DvtLog;

/**
 * 
 * @author CHAMINDA.AMARASINGHE
 *
 */
public class DvtLogDaoImpl extends AbstractDao implements DvtLogDao{

    public void save(List<DvtLog> log) {
        throw new UnsupportedOperationException("not yet");
    }

    public void save(DvtLog log) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = dataSource.getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO DVT_LOG VALUES ");
            sb.append("(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps = con.prepareStatement(sb.toString());
            fillPreparedStatement(log, ps);
            ps.execute();
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    private void fillPreparedStatement(DvtLog log, PreparedStatement ps) throws SQLException {
        ps.setString(1, log.getXmlFileName());
        ps.setString(2, log.getPolicyNo());
        ps.setInt(3, log.getCertificateNo());
        ps.setString(4, log.getRenlCertNo());
        ps.setInt(5, log.getEffDtSeq_no());
        ps.setString(6, log.getPolOfficeCd());
        ps.setString(7, log.getClaimNo());
        ps.setString(8, log.getClmOfficeCd());
        ps.setString(9, log.getTableName());
        ps.setString(10, log.getColName());
        ps.setString(11, log.getColValue());
        ps.setString(12, log.getXmlElementValue());
        ps.setString(13, log.getStatusCd());
        ps.setString(14, log.getEvalCd());
        ps.setString(15, log.getTransTypeDesc());
        ps.setString(16, log.getDomainName());
        ps.setString(17, log.getLobName());
        ps.setInt(18, log.getColOrder());
        ps.setString(19, log.getSourceSystemId());
        ps.setTimestamp(20,  new java.sql.Timestamp(log.getTimestamp().getTime()));
    }

}

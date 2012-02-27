package com.chartis.dvt.core.dao.impl;

import static com.chartis.dvt.commons.utils.StringUtils.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.sql.DataSource;

import com.chartis.dvt.core.model.PolicyKeys;

/**
 * 
 * @author CHAMINDA.AMARASINGHE
 *
 */
class GoldRecordMapperSupport {

    private static Logger logger = Logger.getLogger(GoldRecordMapperSupport.class .getName());
    
    private String table;
    private DataSource dataSource;
    private PolicyKeys policyKeys;

    GoldRecordMapperSupport(String table, PolicyKeys policyKeys, DataSource dataSource) {
        this.table = table;
        this.policyKeys = policyKeys;
        this.dataSource = dataSource;
    }

    Map<String, Object> excute() throws SQLException {
        if (table.equalsIgnoreCase("tclienti")) {
            return tClientAsMap();
        } else if (table.equalsIgnoreCase("tphone")) {
            return tPhoneAsMap();
        } else {
            return recordAsMap();
        }
    }

    private Map<String, Object> recordAsMap() throws SQLException {
        final Map<String, Object> result = new HashMap<String, Object>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = dataSource.getConnection();
            final StringBuilder sb = new StringBuilder();
            sb.append("SELECT * FROM ");
            // TODO make sure safe from sql injection
            sb.append(table);
            sb.append(" WHERE POLICY_NO = ? ");
            sb.append("AND CERTIFICATE_NO = ? ");
            sb.append("AND RENL_CERT_NO = ? ");
            sb.append("AND EFF_DT_SEQ_NO = ? ");
            sb.append("AND POL_OFFICE_CD = ? ");
            logger.info(cat(sb.toString()));
            ps = con.prepareStatement(sb.toString());

            ps.setString(1, policyKeys.getPolicyNo());
            ps.setInt(2, policyKeys.getCertificateNo());
            ps.setString(3, policyKeys.getRenlCertNo());
            ps.setInt(4, policyKeys.getEffDtSeqNo());
            ps.setString(5, policyKeys.getPolOfficeCd());
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                for (int i = 1; i < rsmd.getColumnCount() + 1; i++) {
                    result.put(rsmd.getColumnName(i), rs.getObject(i));
                }
                // we will get first result only
                return result;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        // this should not reach
        return null;
    }

    private Map<String, Object> tPhoneAsMap() throws SQLException {
        final Map<String, Object> result = new HashMap<String, Object>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = dataSource.getConnection();
            final StringBuilder sb = new StringBuilder();
            sb.append("SELECT C.* ");
            sb.append("FROM TPOLICY A, TCLIENT B, TPHONE C ");
            sb.append(" WHERE A.POLICY_NO = ? ");
            sb.append("AND A.CERTIFICATE_NO = ? ");
            sb.append("AND A.RENL_CERT_NO = ? ");
            sb.append("AND A.EFF_DT_SEQ_NO = ? ");
            sb.append("AND A.POL_OFFICE_CD = ? ");
            sb.append("AND A.INS_NAME_CLIENT_NO = B.CLIENT_NO ");
            sb.append("AND B.PHONE_ID = C.PHONE_ID");
            logger.info(cat(sb.toString()));

            ps = con.prepareStatement(sb.toString());

            ps.setString(1, policyKeys.getPolicyNo());
            ps.setInt(2, policyKeys.getCertificateNo());
            ps.setString(3, policyKeys.getRenlCertNo());
            ps.setInt(4, policyKeys.getEffDtSeqNo());
            ps.setString(5, policyKeys.getPolOfficeCd());
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                for (int i = 1; i < rsmd.getColumnCount() + 1; i++) {
                    result.put(rsmd.getColumnName(i), rs.getObject(i));
                }
                // we will get first result only
                return result;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        // this should not reach
        return null;

    }

    private Map<String, Object> tClientAsMap() throws SQLException {
        final Map<String, Object> result = new HashMap<String, Object>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = dataSource.getConnection();
            final StringBuilder sb = new StringBuilder();
            sb.append("SELECT B.* ");
            sb.append("FROM TPOLICY A, TCLIENTI B ");
            sb.append(" WHERE A.POLICY_NO = ? ");
            sb.append("AND A.CERTIFICATE_NO = ? ");
            sb.append("AND A.RENL_CERT_NO = ? ");
            sb.append("AND A.EFF_DT_SEQ_NO = ? ");
            sb.append("AND A.POL_OFFICE_CD = ? ");
            sb.append("AND A.INS_NAME_CLIENT_NO = B.CLIENT_NO");
            logger.info(cat(sb.toString()));

            ps = con.prepareStatement(sb.toString());

            ps.setString(1, policyKeys.getPolicyNo());
            ps.setInt(2, policyKeys.getCertificateNo());
            ps.setString(3, policyKeys.getRenlCertNo());
            ps.setInt(4, policyKeys.getEffDtSeqNo());
            ps.setString(5, policyKeys.getPolOfficeCd());
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                for (int i = 1; i < rsmd.getColumnCount() + 1; i++) {
                    result.put(rsmd.getColumnName(i), rs.getObject(i));
                }
                // we will get first result only
                return result;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        // this should not reach
        return null;
    }
}

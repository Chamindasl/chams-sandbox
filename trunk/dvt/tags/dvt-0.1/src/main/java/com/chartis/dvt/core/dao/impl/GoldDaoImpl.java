/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chartis.dvt.core.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.chartis.dvt.core.dao.AbstractDao;
import com.chartis.dvt.core.dao.GoldDao;
import com.chartis.dvt.core.model.PolicyKeys;

/**
 * 
 * @author chams
 */
public class GoldDaoImpl extends AbstractDao implements GoldDao {

    public Integer getMajorLine(PolicyKeys policyKeys) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = dataSource.getConnection();
            final StringBuilder sb = new StringBuilder();
            sb.append("SELECT B.MAJOR_LINE_CD FROM TPOLICY A, TPRODUCT B ");
            sb.append("WHERE A.POLICY_NO = ? ");
            sb.append("AND A.CERTIFICATE_NO = ? ");
            sb.append("AND A.RENL_CERT_NO = ? ");
            sb.append("AND A.EFF_DT_SEQ_NO = ? ");
            sb.append("AND A.POL_OFFICE_CD = ? ");
            sb.append("AND A.REF_GROUP_CD = B.REF_GROUP_CD ");
            sb.append("AND A.PRODUCT_CD = B.PRODUCT_CD ");
            sb.append("AND A.PRODUCT_EFF_DATE = B.PRODUCT_EFF_DATE ");
            ps = con.prepareStatement(sb.toString());

            ps.setString(1, policyKeys.getPolicyNo());
            ps.setInt(2, policyKeys.getCertificateNo());
            ps.setString(3, policyKeys.getRenlCertNo());
            ps.setInt(4, policyKeys.getEffDtSeqNo());
            ps.setString(5, policyKeys.getPolOfficeCd());
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt("MAJOR_LINE_CD");
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

    public Map<String, Object> getRecordAsMap(final String table, final PolicyKeys policyKeys) throws SQLException {
        return new GoldRecordMapperSupport(table, policyKeys, dataSource).excute();
    }
    
}

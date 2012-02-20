/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chartis.dvt.core.service.impl;

import com.chartis.dvt.core.model.PolicyKeys;
import com.chartis.dvt.core.service.GoldDao;
import javax.sql.DataSource;

/**
 *
 * @author chams
 */
public class GoldDaoImpl implements GoldDao {

    private DataSource dataSource;

    public Integer getMajorLine(PolicyKeys policyKeys) {
//        Connection con = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        try {
//            con = dataSource.getConnection();
//            ps = con.prepareStatement("select * from dvt_columns " + "where table_name = ? order by lob_name");
//            ps.setString(1, lob);
//            rs = ps.executeQuery();
//            DvtTable dvtTable = null;
//            while (rs.next()) {
//                final DvtColumn column = new RowMapper().map(rs);
//                if (dvtTable == null) {
//                    dvtTable = column.getDvtTable();
//                } else {
//                    column.setDvtTable(dvtTable);
//                    dvtTable.addDvtColumn(column);
//                }
//
//            }
//            return dvtColumns;
//        } finally {
//            rs.close();
//            ps.close();
//            con.close();
//        }
        return 1;
    }
}

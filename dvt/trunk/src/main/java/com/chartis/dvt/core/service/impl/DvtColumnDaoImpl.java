package com.chartis.dvt.core.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.chartis.dvt.core.db.model.DvtColumn;
import com.chartis.dvt.core.db.model.DvtColumn.DataType;
import com.chartis.dvt.core.db.model.DvtColumn.EvaluationCode;
import com.chartis.dvt.core.db.model.DvtTable;
import com.chartis.dvt.core.service.DvtColumnDao;

public class DvtColumnDaoImpl implements DvtColumnDao {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<DvtColumn> findAllByLob(final String lob) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        final List<DvtColumn> dvtColumns = new ArrayList<DvtColumn>();
        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement("select * from dvt_columns " + "where table_name = ? order by lob_name");
            ps.setString(1, lob);
            rs = ps.executeQuery();
            DvtTable dvtTable = null;
            while (rs.next()) {
                final DvtColumn column = new RowMapper().map(rs);
                if (dvtTable == null) {
                    dvtTable = column.getDvtTable();
                } else {
                    column.setDvtTable(dvtTable);
                    dvtTable.addDvtColumn(column);
                }

            }
            return dvtColumns;
        } finally {
            rs.close();
            ps.close();
            con.close();
        }
    }

    private static class RowMapper {
        DvtColumn map(final ResultSet rs) throws SQLException {
            final DvtColumn dvtColumn = new DvtColumn();
            final DvtTable dvtTable = new DvtTable();
            dvtTable.setLineOfBusiness(rs.getString("LOB_NAME"));
            dvtTable.setName(rs.getString("TABLE_NAME"));
            dvtColumn.setName(rs.getString("COL_NAME"));
            dvtColumn.setDataType(DataType.VARCHAR);
            dvtColumn.setXmlElement(rs.getString("XML_ELEMENT_NAME"));
            dvtColumn.setXpath(rs.getString("XML_XPATH"));
            dvtColumn.setEvaluationCode(EvaluationCode.get(rs.getInt("EVAL_CD")));
            dvtColumn.setDvtTable(dvtTable);
            return dvtColumn;
        }
    }
}

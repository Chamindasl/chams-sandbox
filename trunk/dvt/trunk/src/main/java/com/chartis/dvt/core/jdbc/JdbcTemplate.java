package com.chartis.dvt.core.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import com.chartis.dvt.core.db.model.DvtColumn;

public class JdbcTemplate {

    private DataSource dataSource;
    
    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

//    public <T> List<T> queryForList (final String sql, final Object [] params,
//            final RowMapper<T> rowMapper ){
//        Connection s = dataSource.getConnection();
//        Statement ss  = s.createStatement();
//        ss.executeQuery(sql);
//        final List<T> result = new ArrayList<T>();
//        return rowMapper.map(null);
//    }
    
}

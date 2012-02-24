package com.chartis.dvt.core.dao;

import javax.sql.DataSource;

public abstract class AbstractDao {

    protected DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
}

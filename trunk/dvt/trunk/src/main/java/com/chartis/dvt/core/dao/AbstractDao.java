package com.chartis.dvt.core.dao;

import javax.sql.DataSource;

/**
 * 
 * @author CHAMINDA.AMARASINGHE
 *
 */
public abstract class AbstractDao {

    protected DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
}

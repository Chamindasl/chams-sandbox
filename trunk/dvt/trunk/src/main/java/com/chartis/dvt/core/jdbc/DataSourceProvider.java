package com.chartis.dvt.core.jdbc;

import javax.sql.DataSource;

/**
 * 
 * @author CHAMINDA.AMARASINGHE
 *
 */
public interface DataSourceProvider {
    DataSource getDataSource();
    
}

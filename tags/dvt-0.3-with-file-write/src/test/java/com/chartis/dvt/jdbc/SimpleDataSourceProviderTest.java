package com.chartis.dvt.jdbc;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.junit.Before;
import org.junit.Test;

import com.chartis.dvt.core.jdbc.SimpleDataSourceProvider;

/**
 * 
 * @author CHAMINDA.AMARASINGHE
 *
 */
public class SimpleDataSourceProviderTest {

    private SimpleDataSourceProvider dataSourceProvider;
    
    public SimpleDataSourceProviderTest() {
    }
    
    @Before
    public void setUp() {
        dataSourceProvider = new SimpleDataSourceProvider();
    }

    @Test
    public void testDataSource() throws SQLException {
        final DataSource dataSource = dataSourceProvider.getDataSource();
        assertNotNull(dataSource);
        assertNotNull(dataSource.getConnection().createStatement().execute("select * from DVT_TABLES"));
    }
}

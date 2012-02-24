/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chartis.dvt.jdbc;

import java.sql.SQLException;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author chams
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

package com.chartis.dvt.core.service.impl;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.chartis.dvt.core.dao.impl.DvtColumnDaoImpl;
import com.chartis.dvt.core.jdbc.SimpleDataSourceProvider;

/**
 * 
 * @author CHAMINDA.AMARASINGHE
 *
 */
public class DvtColumnDaoImplTest {

    private DvtColumnDaoImpl columnDaoImpl;
    
    @Before
    public void before() {
        columnDaoImpl = new DvtColumnDaoImpl();
        columnDaoImpl.setDataSource(new SimpleDataSourceProvider().getDataSource());
    }
    
    @Test
    public final void testFindAllByTable() throws SQLException {
        assertTrue(columnDaoImpl.findAllByTable("tpolkey").size() > 0);
    }

}

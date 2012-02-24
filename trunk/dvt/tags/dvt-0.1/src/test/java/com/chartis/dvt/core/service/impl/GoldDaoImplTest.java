package com.chartis.dvt.core.service.impl;

import static org.junit.Assert.*;
import static com.chartis.dvt.core.test.util.TestData.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import com.chartis.dvt.core.dao.impl.GoldDaoImpl;
import com.chartis.dvt.jdbc.SimpleDataSourceProvider;

public class GoldDaoImplTest {

    private GoldDaoImpl goldDaoImpl;
    @Before
    public void setUp() throws Exception {
        goldDaoImpl = new GoldDaoImpl();
        goldDaoImpl.setDataSource(new SimpleDataSourceProvider().getDataSource());
    }

    @Test
    public final void testGetMajorLine() throws SQLException {
        assertEquals(2, goldDaoImpl.getMajorLine(samplePolicyKeys()).intValue());
    }

    @Test
    public final void testGetRecordAsMap() throws SQLException {
        assertEquals(27, goldDaoImpl.getRecordAsMap("tpolkey", samplePolicyKeys()).size());
    }

}

package com.chartis.dvt.core;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import javax.sql.DataSource;

import org.junit.Test;

import com.chartis.dvt.core.dao.DvtColumnDao;
import com.chartis.dvt.core.dao.DvtLogDao;
import com.chartis.dvt.core.dao.GoldDao;

/**
 * 
 * @author CHAMINDA.AMARASINGHE
 *
 */
public class DaoServiceLocatorTest {

    @Test
    public final void testSingalton() {
        assertSame(DaoServiceLocator.getInstance(), DaoServiceLocator.getInstance());
    }

    @Test
    public final void testGettingDao() {
        assertNotNull((DaoServiceLocator.getInstance().service(DataSource.class)));
        assertNotNull((DaoServiceLocator.getInstance().service(GoldDao.class)));
        assertNotNull((DaoServiceLocator.getInstance().service(DvtColumnDao.class)));
        assertNotNull((DaoServiceLocator.getInstance().service(DvtLogDao.class)));
    }

}

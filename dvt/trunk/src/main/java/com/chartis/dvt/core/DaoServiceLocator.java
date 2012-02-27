package com.chartis.dvt.core;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.chartis.dvt.core.dao.DvtColumnDao;
import com.chartis.dvt.core.dao.DvtLogDao;
import com.chartis.dvt.core.dao.GoldDao;
import com.chartis.dvt.core.dao.impl.DvtColumnDaoImpl;
import com.chartis.dvt.core.dao.impl.DvtLogDaoImpl;
import com.chartis.dvt.core.dao.impl.GoldDaoImpl;
import com.chartis.dvt.core.jdbc.SimpleDataSourceProvider;

public class DaoServiceLocator {

    private Map<String, Object> services = new HashMap<String, Object>();
    
    private DaoServiceLocator() {
        buildDataSource();
        buildGoldDao();
        buildDvtColumnDao();
        buildDvtLogDao();
    }
    
    private void buildDvtLogDao() {
        final DvtLogDaoImpl dvtLogDaoImpl = new DvtLogDaoImpl();
        dvtLogDaoImpl.setDataSource(service(DataSource.class));
        services.put(DvtLogDao.class.getName(), dvtLogDaoImpl);
    }

    private void buildDvtColumnDao() {
        final DvtColumnDaoImpl dvtColumnDaoImpl = new DvtColumnDaoImpl();
        dvtColumnDaoImpl.setDataSource(service(DataSource.class));
        services.put(DvtColumnDao.class.getName(), dvtColumnDaoImpl);
    }

    private void buildGoldDao() {
        final GoldDaoImpl goldDaoImpl = new GoldDaoImpl();
        goldDaoImpl.setDataSource(service(DataSource.class));
        services.put(GoldDao.class.getName(), goldDaoImpl);
    }

    private void buildDataSource() {
        services.put(DataSource.class.getName(), new SimpleDataSourceProvider().getDataSource());
    }

    @SuppressWarnings("unchecked")
    public <T> T service (final Class<T> t) {
        if (t.getName().equals(GoldDao.class.getName())) {
            return (T)services.get(t.getClass().getName()); 
        }
        return null;
    }
    
    private static class Iodh {
        private static DaoServiceLocator iodhServiceLocator = new DaoServiceLocator();
    }

    public static DaoServiceLocator getInstance() {
        return Iodh.iodhServiceLocator;
    }
}

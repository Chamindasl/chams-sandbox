package com.chartis.dvt.core;

import static com.chartis.dvt.commons.utils.StringUtils.cat;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.sql.DataSource;

import com.chartis.dvt.core.dao.DvtColumnDao;
import com.chartis.dvt.core.dao.DvtLogDao;
import com.chartis.dvt.core.dao.GoldDao;
import com.chartis.dvt.core.dao.impl.DvtColumnDaoImpl;
import com.chartis.dvt.core.dao.impl.DvtLogDaoImpl;
import com.chartis.dvt.core.dao.impl.GoldDaoImpl;
import com.chartis.dvt.core.jdbc.SimpleDataSourceProvider;

/**
 * 
 * @author CHAMINDA.AMARASINGHE
 *
 */
public class DaoServiceLocator {

    private static Logger logger = Logger.getLogger(DaoServiceLocator.class.getName());
    
    private Map<String, Object> services = new HashMap<String, Object>();

    private DaoServiceLocator() {
        buildDataSource();
        buildGoldDao();
        buildDvtColumnDao();
        buildDvtLogDao();
    }

    private void buildDvtLogDao() {
        logger.info(cat("Building ", DvtLogDao.class.getName()));
        final DvtLogDaoImpl dvtLogDaoImpl = new DvtLogDaoImpl();
        dvtLogDaoImpl.setDataSource(service(DataSource.class));
        services.put(DvtLogDao.class.getName(), dvtLogDaoImpl);
    }

    private void buildDvtColumnDao() {
        logger.info(cat("Building ", DvtColumnDao.class.getName()));
        final DvtColumnDaoImpl dvtColumnDaoImpl = new DvtColumnDaoImpl();
        dvtColumnDaoImpl.setDataSource(service(DataSource.class));
        services.put(DvtColumnDao.class.getName(), dvtColumnDaoImpl);
    }

    private void buildGoldDao() {
        logger.info(cat("Building ", GoldDao.class.getName()));
        final GoldDaoImpl goldDaoImpl = new GoldDaoImpl();
        goldDaoImpl.setDataSource(service(DataSource.class));
        services.put(GoldDao.class.getName(), goldDaoImpl);
    }

    private void buildDataSource() {
        logger.info(cat("Building ", GoldDao.class.getName()));
        services.put(DataSource.class.getName(), new SimpleDataSourceProvider().getDataSource());
    }

    @SuppressWarnings("unchecked")
    public <T> T service(final Class<T> t) {
        logger.info(cat("Request for ", t.getName()));
        return (T) services.get(t.getName());
    }

    private static class Iodh {
        private static DaoServiceLocator iodhServiceLocator = new DaoServiceLocator();
    }

    public static DaoServiceLocator getInstance() {
        return Iodh.iodhServiceLocator;
    }
}

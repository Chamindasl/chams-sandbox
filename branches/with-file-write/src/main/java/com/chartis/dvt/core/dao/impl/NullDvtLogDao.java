package com.chartis.dvt.core.dao.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import com.chartis.dvt.commons.utils.StringUtils;
import com.chartis.dvt.core.db.model.DvtLog;
import com.chartis.dvt.core.io.DvtLogIoDao;

public class NullDvtLogDao implements DvtLogIoDao {

    private static Logger logger = Logger.getLogger(NullDvtLogDao.class.getName());

    public void save(List<DvtLog> log) {
    }

    public void save(DvtLog log) throws SQLException, IOException {
        logger.fine(StringUtils.cat("Logging on null NullDvtLogDao ", log));
    }

    public void close() throws IOException {
    }

    public void flush() throws IOException {
    }

}

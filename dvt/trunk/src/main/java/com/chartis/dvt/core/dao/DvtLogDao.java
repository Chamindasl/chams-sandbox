package com.chartis.dvt.core.dao;

import java.sql.SQLException;
import java.util.List;

import com.chartis.dvt.core.db.model.DvtLog;

public interface DvtLogDao {

    void save(final List<DvtLog> log);
    void save(final DvtLog log) throws SQLException;

}

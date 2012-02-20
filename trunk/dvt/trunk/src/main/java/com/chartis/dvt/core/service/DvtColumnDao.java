package com.chartis.dvt.core.service;

import java.sql.SQLException;
import java.util.List;

import com.chartis.dvt.core.db.model.DvtColumn;

public interface DvtColumnDao {

    List<DvtColumn> findAllByLob(final String lob) throws SQLException;
}

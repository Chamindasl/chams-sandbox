package com.chartis.dvt.core.jdbc;

import java.sql.ResultSet;

public interface RowMapper<T> {

    T map(ResultSet rs);
}

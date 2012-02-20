package com.chartis.dvt.jdbc;

import java.sql.ResultSet;

public interface RowMapper<T> {

    T map(ResultSet rs);
}

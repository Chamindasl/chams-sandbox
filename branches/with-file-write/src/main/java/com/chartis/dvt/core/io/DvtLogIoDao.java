package com.chartis.dvt.core.io;

import java.io.Closeable;
import java.io.Flushable;

import com.chartis.dvt.core.dao.DvtLogDao;

public interface DvtLogIoDao extends DvtLogDao, Closeable, Flushable{
}

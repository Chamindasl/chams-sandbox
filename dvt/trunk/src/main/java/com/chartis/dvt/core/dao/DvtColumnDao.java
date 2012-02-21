package com.chartis.dvt.core.dao;

import java.sql.SQLException;
import java.util.List;

import com.chartis.dvt.core.db.model.DvtColumn;
import com.chartis.dvt.core.db.model.DvtTable;
import com.chartis.dvt.core.model.LineOfBusiness;

/**
 * 
 * @author CHAMINDA.AMARASINGHE
 *
 */
public interface DvtColumnDao {

    /**
     * 
     * @param table
     * @return
     * @throws SQLException
     */
    List<DvtColumn> findAllByTable(final String table) throws SQLException;
    
    /**
     * 
     * @param lob
     * @return
     * @throws SQLException
     */
    List<DvtColumn> findAllByLob(final LineOfBusiness lob) throws SQLException;
}

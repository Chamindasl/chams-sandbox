/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chartis.dvt.core.dao;

import java.sql.SQLException;
import java.util.Map;

import com.chartis.dvt.core.model.PolicyKeys;

/**
 * 
 * @author CHAMINDA.AMARASINGHE
 *
 */
public interface GoldDao {
    Integer getMajorLine(final PolicyKeys policyKeys) throws SQLException;
    Map<String, Object> getRecordAsMap(final String table, final PolicyKeys policyKeys) throws SQLException;
}

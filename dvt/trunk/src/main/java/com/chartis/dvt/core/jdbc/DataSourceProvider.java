/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chartis.dvt.core.jdbc;

import javax.sql.DataSource;

/**
 *
 * @author chams
 */
public interface DataSourceProvider {
    DataSource getDataSource();
    
}

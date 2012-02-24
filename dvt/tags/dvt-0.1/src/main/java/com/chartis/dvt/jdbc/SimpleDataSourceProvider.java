/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chartis.dvt.jdbc;

import java.util.ResourceBundle;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

/**
 *
 * @author chams
 */
public class SimpleDataSourceProvider implements DataSourceProvider {

    public DataSource getDataSource() {
        final ResourceBundle bundle = java.util.ResourceBundle.getBundle("jdbc");
        PoolProperties p = new PoolProperties();
        p.setUrl(bundle.getString("jdbc.url"));
        p.setDriverClassName(bundle.getString("jdbc.driver"));
        p.setUsername(bundle.getString("jdbc.username"));
        p.setPassword(bundle.getString("jdbc.password"));
        p.setJmxEnabled(true);
        p.setTestWhileIdle(false);
        p.setTestOnBorrow(true);
        p.setValidationQuery("SELECT 1");
        p.setTestOnReturn(false);
        p.setValidationInterval(30000);
        p.setTimeBetweenEvictionRunsMillis(30000);
        p.setMaxActive(100);
        p.setInitialSize(10);
        p.setMaxWait(10000);
        p.setRemoveAbandonedTimeout(60);
        p.setMinEvictableIdleTimeMillis(30000);
        p.setMinIdle(10);
        p.setLogAbandoned(true);
        p.setRemoveAbandoned(true);
        final DataSource datasource = new DataSource();
        datasource.setPoolProperties(p);
        return datasource;
    }
}

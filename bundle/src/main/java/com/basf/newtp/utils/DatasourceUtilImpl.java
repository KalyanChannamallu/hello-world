package com.basf.newtp.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.sql.DataSource;
import com.day.commons.datasource.poolservice.DataSourceNotFoundException;
import com.day.commons.datasource.poolservice.DataSourcePool;
 
/**
 *
 * This class is an example if how DataSourcePool can be used to obtain the DataSource.
 *
 * @scr.component immediate="true" metatype="no"
 * @scr.service interface="DatasourceUtil"
 * @scr.property name="service.description" value="Data Source lookup example"
 * @scr.property name="service.vendor" value="Day Software"
 */
 
public class DatasourceUtilImpl implements DatasourceUtil {
    private static final Logger log = LoggerFactory.getLogger(DatasourceUtilImpl.class);
 
    /** @scr.reference policy="static" */
    private DataSourcePool dataSourceService;
 
    public DataSource getDataSource(String dataSourceName) {
        log.info("Using DataSourcePool service lookup " + "to get connection pool " + dataSourceName); 
        DataSource dataSource = null;
        try {
            dataSource = (DataSource) dataSourceService.getDataSource(dataSourceName);
        } catch (DataSourceNotFoundException e) {
            log.error("Unable to find datasource {}.", dataSourceName, e);
        }
        return dataSource;
    }
}
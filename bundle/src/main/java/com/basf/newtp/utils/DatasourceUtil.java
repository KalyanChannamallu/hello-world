package com.basf.newtp.utils;

import javax.sql.DataSource;

public interface DatasourceUtil {
    public DataSource getDataSource(String dataSourceName);
}
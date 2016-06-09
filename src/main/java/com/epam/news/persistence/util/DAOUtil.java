package com.epam.news.persistence.util;

import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


/**
 * DAO Utils.
 */
public final class DAOUtil {

    private static final Logger LOG = Logger.getLogger(DAOUtil.class);

    /**
     * This method simplify process of releasing connection to data source.
     *
     * @param connection the connection
     * @param dataSource the data source
     */
    public static void releaseConnection(Connection connection, DataSource dataSource) {
        if (connection != null) {
            try {
                DataSourceUtils.doReleaseConnection(connection, dataSource);
            } catch (SQLException e) {
                LOG.error("Unable to release connection to data source", e);
            }
        }
    }
}

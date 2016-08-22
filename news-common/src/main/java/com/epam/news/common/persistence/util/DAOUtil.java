package com.epam.news.common.persistence.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * DAO Utils.
 */
public final class DAOUtil {

    private static final Logger LOG = LogManager.getLogger(DAOUtil.class);

    private DAOUtil() {}

    /**
     *
     * Close statement and log info in case when error occurred
     *
     * @param statement the statement to be closed
     */
    public static void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOG.error("Unable to close statement", e);
            }
        }
    }

    /**
     * This method is to simplify process of releasing connection to data source.
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

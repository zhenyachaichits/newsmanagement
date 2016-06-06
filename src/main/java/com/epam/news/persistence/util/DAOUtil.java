package com.epam.news.persistence.util;

import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Yauhen_Chaichyts on 5/30/2016.
 */
public final class DAOUtil {

    public static final String ARRAY_DATA_TYPE = "LONG";

    public static void releaseConnection(Connection connection, DataSource dataSource) {
        if (connection != null) {
            try {
                DataSourceUtils.doReleaseConnection(connection, dataSource);
            } catch (SQLException e) {
                // TODO: 04.06.2016 add logging
                //throw new DAOException("Couldn't close connection", e);
            }
        }
    }
}

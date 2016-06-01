package com.epam.news.dao.util;

import com.epam.news.dao.exception.DAOException;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Yauhen_Chaichyts on 5/30/2016.
 */
// TODO: 5/30/2016 it's stupid
public final class DAOUtil {

    public static final String ARRAY_DATA_TYPE = "NUMBER";

    public static void releaseConnection(Connection connection, DataSource dataSource) throws DAOException {
        if (connection != null) {
            try {
                DataSourceUtils.doReleaseConnection(connection, dataSource);
            } catch (SQLException e) {
                throw new DAOException("Couldn't close connection", e);
            }
        }
    }
}

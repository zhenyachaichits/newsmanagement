package com.epam.news.persistence.oracle;

import com.epam.news.persistence.UserDAO;
import com.epam.news.persistence.exception.DAOException;
import com.epam.news.persistence.util.DAOUtil;
import com.epam.news.persistence.util.processor.EntityProcessor;
import com.epam.news.persistence.util.processor.exception.EntityProcessorException;
import com.epam.news.persistence.util.processor.impl.UserProcessor;
import com.epam.news.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

/**
 * Created by Yauhen_Chaichyts on 5/27/2016.
 */
@Repository
public class UserDAOImpl implements UserDAO {

    private static final String SQL_ADD_USER_QUERY = "INSERT INTO USERS (USER_NAME, LOGIN, PASSWORD) " +
            "VALUES (?, ?, ?)";
    private static final String SQL_GET_ALL_USERS_QUERY = "SELECT USER_ID, USER_NAME, LOGIN, PASSWORD FROM USERS";
    private static final String SQL_FIND_USER_BY_ID_QUERY = "SELECT USER_ID, USER_NAME, LOGIN, PASSWORD FROM USERS " +
            "WHERE USER_ID = ?";
    private static final String SQL_UPDATE_USER_QUERY = "UPDATE USERS SET USER_NAME = ?, LOGIN = ?, PASSWORD = ? " +
            "WHERE USER_ID = ?";
    private static final String SQL_DELETE_USER_QUERY = "DELETE FROM USERS WHERE USER_ID = ?";

    @Autowired
    private DataSource dataSource;
    @Autowired
    private EntityProcessor<User> entityProcessor;

    // TODO: 5/30/2016 add password encryption
    public User add(User user) throws DAOException {
        Connection connection = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            String[] generatedKeyName = {UserProcessor.USER_ID_KEY};
            PreparedStatement statement = connection.prepareStatement(SQL_ADD_USER_QUERY, generatedKeyName);

            statement.setString(1, user.getUserName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setUserId(resultSet.getLong(1));
            }

            return user;
        } catch (SQLException e) {
            throw new DAOException("Couldn't add new user", e);
        } finally {
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    public User find(Long id) throws DAOException {
        Connection connection = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_ID_QUERY);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            return entityProcessor.toEntity(resultSet);
        } catch (SQLException | EntityProcessorException e) {
            throw new DAOException("Couldn't find user by id", e);
        } finally {
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    public boolean update(User user) throws DAOException {
        Connection connection = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_USER_QUERY);

            statement.setString(1, user.getUserName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getPassword());
            statement.setLong(4, user.getUserId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("Couldn't update user", e);
        } finally {
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    public boolean delete(Long id) throws DAOException {
        Connection connection = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_USER_QUERY);

            statement.setLong(1, id);
            return statement.execute();
        } catch (SQLException e) {
            throw new DAOException("Couldn't delete user with id", e);
        } finally {
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    public List<User> all() throws DAOException {
        Connection connection = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_USERS_QUERY);

            return entityProcessor.toEntityList(resultSet);
        } catch (SQLException | EntityProcessorException e) {
            throw new DAOException("Couldn't get all users", e);
        } finally {
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }
}

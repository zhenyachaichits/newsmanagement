package com.epam.news.dao.impl;

import com.epam.news.dao.RoleDAO;
import com.epam.news.dao.exception.DAOException;
import com.epam.news.dao.util.DAOUtil;
import com.epam.news.dao.util.processor.EntityProcessor;
import com.epam.news.dao.util.processor.exception.EntityProcessorException;
import com.epam.news.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

/**
 * Created by Yauhen_Chaichyts on 5/30/2016.
 */
@Repository
public class RoleDAOImpl implements RoleDAO {

    private static final String SQL_ADD_ROLE_QUERY = "INSERT INTO ROLES (USER_ID, ROLE_NAME) VALUES (?, ?)";
    private static final String SQL_FIND_ROLE_BY_USER_ID = "SELECT USER_ID, ROLE_NAME FROM ROLES WHERE USER_ID = ?";
    private static final String SQL_UPDATE_ROLE_QUERY = "UPDATE ROLES SET ROLE_NAME = ? WHERE USER_ID = ?";
    private static final String SQL_DELETE_ROLE_QUERY = "DELETE FROM ROLES WHERE USER_ID = ?";
    private static final String SQL_GET_ALL_ROLES_QUERY = "SELECT USER_ID, ROLE_NAME FROM ROLES";

    @Autowired
    private DataSource dataSource;
    @Autowired
    private EntityProcessor<Role> entityProcessor;

    @Override
    public Role add(Role role) throws DAOException {
        Connection connection = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            PreparedStatement statement = connection.prepareStatement(SQL_ADD_ROLE_QUERY);

            statement.setLong(1, role.getUserId());
            statement.setString(2, role.getRoleName());
            statement.executeUpdate();

            return role;
        } catch (SQLException e) {
            throw new DAOException("Couldn't add new role", e);
        } finally {
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    @Override
    public Role find(Long userId) throws DAOException {
        Connection connection = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ROLE_BY_USER_ID);

            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            Role role = entityProcessor.toEntity(resultSet);

            return role;
        } catch (SQLException | EntityProcessorException e) {
            throw new DAOException("Couldn't find role by id", e);
        } finally {
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    @Override
    public boolean update(Role role) throws DAOException {
        Connection connection = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_ROLE_QUERY);

            statement.setString(1, role.getRoleName());
            statement.setLong(2, role.getUserId());

            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DAOException("Couldn't update role", e);
        } finally {
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    @Override
    public boolean delete(Long userId) throws DAOException {
        Connection connection = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_ROLE_QUERY);

            statement.setLong(1, userId);
            return statement.execute();
        } catch (SQLException e) {
            throw new DAOException("Couldn't delete role with user id", e);
        } finally {
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    @Override
    public List<Role> all() throws DAOException {
        Connection connection = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_ROLES_QUERY);
            List<Role> roleList = entityProcessor.toEntityList(resultSet);

            return roleList;
        } catch (SQLException | EntityProcessorException e) {
            throw new DAOException("Couldn't get all roles", e);
        } finally {
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }
}

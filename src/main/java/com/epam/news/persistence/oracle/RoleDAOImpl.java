package com.epam.news.persistence.oracle;

import com.epam.news.persistence.RoleDAO;
import com.epam.news.persistence.exception.DAOException;
import com.epam.news.persistence.util.DAOUtil;
import com.epam.news.persistence.util.processor.EntityProcessor;
import com.epam.news.persistence.util.processor.exception.EntityProcessorException;
import com.epam.news.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

/**
 * Oracle Role data access object. Provides operations with role table in database.
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

    /**
     * Add new user role record to database
     *
     * @param role to add
     * @return added role
     * @throws DAOException if SQLException thrown
     */
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

    /**
     * Search user role by user id
     *
     * @param userId id of user
     * @return found role
     * @throws DAOException if SQLException or EntityProcessorException thrown
     */
    @Override
    public Role find(Long userId) throws DAOException {
        Connection connection = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ROLE_BY_USER_ID);

            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();

            return entityProcessor.toEntity(resultSet);
        } catch (SQLException | EntityProcessorException e) {
            throw new DAOException("Couldn't find role by id", e);
        } finally {
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }

    /**
     * Update user role record
     *
     * @param role to be updated
     * @return true in case of success
     * @throws DAOException if SQLException thrown
     */
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

    /**
     * Delete record from database by user id
     *
     * @param userId news id
     * @return true in case of success
     * @throws DAOException if SQLException thrown
     */
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

    /**
     * Get all user roles
     *
     * @return list of roles
     * @throws DAOException if SQLException or EntityProcessorException thrown
     */
    @Override
    public List<Role> all() throws DAOException {
        Connection connection = null;
        try {
            connection = DataSourceUtils.getConnection(dataSource);
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_ROLES_QUERY);

            return entityProcessor.toEntityList(resultSet);
        } catch (SQLException | EntityProcessorException e) {
            throw new DAOException("Couldn't get all roles", e);
        } finally {
            DAOUtil.releaseConnection(connection, dataSource);
        }
    }
}

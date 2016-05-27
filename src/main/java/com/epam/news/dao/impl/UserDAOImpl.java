package com.epam.news.dao.impl;

import com.epam.news.dao.UserDAO;
import com.epam.news.dao.exception.DAOException;
import com.epam.news.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by Yauhen_Chaichyts on 5/27/2016.
 */
@Repository
public class UserDAOImpl implements UserDAO {

    private static final String ADD_QUERY = "INSERT INTO USERS (USER_NAME, LOGIN, PASSWORD) " +
            "VALUES (?, ?, ?)";
    private static final String ALL_QUERY = "SELECT * FROM USERS";

    @Autowired
    private DataSource dataSource;

    public User add(User entity) throws DAOException {
        return null;
    }

    public User find(Long domain) throws DAOException {
        return null;
    }

    public User update(User entity) throws DAOException {
    return null;
}

    public User delete(Long domain) throws DAOException {
        return null;
    }

    public List<User> all() throws DAOException {
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();

            statement.executeQuery(ALL_QUERY);

            return null;
        } catch (SQLException e) {
            throw new DAOException("Couldn't get all users", e);
        }
    }
}

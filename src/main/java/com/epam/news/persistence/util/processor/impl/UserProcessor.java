package com.epam.news.persistence.util.processor.impl;

import com.epam.news.domain.User;
import com.epam.news.persistence.util.processor.EntityProcessor;
import com.epam.news.exception.EntityProcessorException;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The User processor. Extracts user data from result set and contains comment keys.
 */
@Component
public class UserProcessor implements EntityProcessor<User> {

    public static final String USER_ID_KEY = "USER_ID";
    public static final String USER_NAME_KEY = "USER_NAME";
    public static final String LOGIN_KEY = "LOGIN";
    public static final String PASSWORD_KEY = "PASSWORD";

    /**
     * Extracts user object from result set.
     *
     * @param resultSet the result set
     * @return extracted user
     * @throws EntityProcessorException in case of result set is empty
     *                                  or any exception was thrown in method
     */
    @Override
    public User toEntity(ResultSet resultSet) throws EntityProcessorException {
        try {
            if (!resultSet.next()) {
                throw new EntityProcessorException("Result set is empty");
            }

            return getEntity(resultSet);
        } catch (SQLException | EntityProcessorException e) {
            throw new EntityProcessorException("Couldn't get user from result set", e);
        }
    }

    /**
     * Extracts the list of users from result set
     *
     * @param resultSet the result set
     * @return the list of extracted users
     * @throws EntityProcessorException if any exception was thrown in method
     */
    @Override
    public List<User> toEntityList(ResultSet resultSet) throws EntityProcessorException {
        try {
            List<User> userList = new ArrayList<>();

            while (resultSet.next()) {
                userList.add(getEntity(resultSet));
            }

            return userList;
        } catch (SQLException e) {
            throw new EntityProcessorException("Couldn't get user list from result set", e);
        }
    }

    private User getEntity(ResultSet resultSet) throws SQLException {
        User user = new User();

        long userId = resultSet.getLong(USER_ID_KEY);
        String userName = resultSet.getString(USER_NAME_KEY);
        String login = resultSet.getString(LOGIN_KEY);
        String password = resultSet.getString(PASSWORD_KEY);

        user.setUserId(userId);
        user.setUserName(userName);
        user.setLogin(login);
        user.setPassword(password);

        return user;
    }

}

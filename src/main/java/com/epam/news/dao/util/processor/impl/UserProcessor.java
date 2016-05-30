package com.epam.news.dao.util.processor.impl;

import com.epam.news.dao.util.processor.EntityProcessor;
import com.epam.news.dao.util.processor.exception.EntityProcessorException;
import com.epam.news.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zheny Chaichits on 28.05.2016.
 */
public class UserProcessor implements EntityProcessor<User> {

    // TODO: 28.05.2016  probably can be replaced with enum
    private static final String USER_ID_KEY = "USER_ID";
    private static final String USER_NAME_KEY = "USER_NAME";
    private static final String LOGIN_KEY = "LOGIN";
    private static final String PASSWORD_KEY = "PASSWORD";

    private UserProcessor() {
    }

    private static final class UserProcessorHolder {
        public static final UserProcessor INSTANCE = new UserProcessor();
    }

    public UserProcessor getInstance() {
        return UserProcessorHolder.INSTANCE;
    }

    @Override
    public User toEntity(ResultSet resultSet) throws EntityProcessorException {
        try {
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
        } catch (SQLException e) {
            throw new EntityProcessorException("Couldn't get user from result set", e);
        }
    }

    @Override
    public List<User> toEntityList(ResultSet resultSet) throws EntityProcessorException {
        try {
            List<User> userList = new ArrayList<>();

            while (resultSet.next()) {
                userList.add(toEntity(resultSet));
            }

            return userList;
        } catch (SQLException | EntityProcessorException e) {
            throw new EntityProcessorException("Couldn't get user list from result set", e);
        }
    }
}

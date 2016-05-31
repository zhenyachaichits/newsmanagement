package com.epam.news.dao.util.processor.impl;

import com.epam.news.dao.util.processor.EntityProcessor;
import com.epam.news.dao.util.processor.exception.EntityProcessorException;
import com.epam.news.domain.Role;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yauhen_Chaichyts on 5/30/2016.
 */
@Component
public class RoleProcessor implements EntityProcessor<Role> {

    public static final String USER_ID_KEY = "USER_ID";
    public static final String ROLE_NAME_KEY = "ROLE_NAME";

    @Override
    public Role toEntity(ResultSet resultSet) throws EntityProcessorException {
        try {
            Role role = new Role();

            long userId = resultSet.getLong(USER_ID_KEY);
            String roleName = resultSet.getString(ROLE_NAME_KEY);

            role.setUserId(userId);
            role.setRoleName(roleName);

            return role;
        } catch (SQLException e) {
            throw new EntityProcessorException("Couldn't get role from result set", e);
        }
    }

    @Override
    public List<Role> toEntityList(ResultSet resultSet) throws EntityProcessorException {
        try {
            List<Role> roleList = new ArrayList<>();

            while (resultSet.next()) {
                roleList.add(toEntity(resultSet));
            }

            return roleList;
        } catch (SQLException | EntityProcessorException e) {
            throw new EntityProcessorException("Couldn't get role list from result set", e);
        }
    }
}

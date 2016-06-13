package com.epam.news.persistence.util.processor.impl;

import com.epam.news.domain.Role;
import com.epam.news.persistence.util.processor.EntityProcessor;
import com.epam.news.exception.EntityProcessorException;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The Role processor. Extracts user access level data from result set and contains comment keys.
 */
@Component
public class RoleProcessor implements EntityProcessor<Role> {

    public static final String USER_ID_KEY = "USER_ID";
    public static final String ROLE_NAME_KEY = "ROLE_NAME";

    /**
     * Extracts user role object from result set.
     *
     * @param resultSet the result set
     * @return extracted user role
     * @throws EntityProcessorException in case of result set is empty
     *                                  or any exception was thrown in method
     */
    @Override
    public Role toEntity(ResultSet resultSet) throws EntityProcessorException {
        try {
            if (!resultSet.next()) {
                throw new EntityProcessorException("Result set is empty");
            }

            return getEntity(resultSet);
        } catch (SQLException | EntityProcessorException e) {
            throw new EntityProcessorException("Couldn't get role from result set", e);
        }
    }

    /**
     * Extracts the list of roles from result set
     *
     * @param resultSet the result set
     * @return the list of extracted roles
     * @throws EntityProcessorException if any exception was thrown in method
     */
    @Override
    public List<Role> toEntityList(ResultSet resultSet) throws EntityProcessorException {
        try {
            List<Role> roleList = new ArrayList<>();

            while (resultSet.next()) {
                roleList.add(getEntity(resultSet));
            }

            return roleList;
        } catch (SQLException e) {
            throw new EntityProcessorException("Couldn't get role list from result set", e);
        }
    }

    private Role getEntity(ResultSet resultSet) throws SQLException {
        Role role = new Role();

        long userId = resultSet.getLong(USER_ID_KEY);
        String roleName = resultSet.getString(ROLE_NAME_KEY);

        role.setUserId(userId);
        role.setRoleName(roleName);

        return role;
    }
}

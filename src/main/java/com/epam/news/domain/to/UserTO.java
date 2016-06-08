package com.epam.news.domain.to;

import com.epam.news.domain.User;
import com.epam.news.domain.Role;


/**
 * The type User to.
 */
public class UserTO {
    private User user;
    private Role role;

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserTO userTO = (UserTO) o;

        if (user != null ? !user.equals(userTO.user) : userTO.user != null) return false;
        return role != null ? role.equals(userTO.role) : userTO.role == null;

    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}

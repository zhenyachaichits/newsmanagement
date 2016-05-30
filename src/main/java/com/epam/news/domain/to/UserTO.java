package com.epam.news.domain.to;

import com.epam.news.domain.User;
import com.epam.news.domain.Role;

/**
 * Created by Zheny Chaichits on 26.05.2016.
 */
public class UserTO {
    private User user;
    private Role role;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

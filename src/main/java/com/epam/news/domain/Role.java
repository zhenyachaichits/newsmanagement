package com.epam.news.domain;

/**
 * Created by Zheny Chaichits on 26.05.2016.
 */
public class Role {
    private long userId;
    private String roleName;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}

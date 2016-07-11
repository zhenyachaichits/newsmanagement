package com.epam.news.admin.security.domain;

import com.epam.news.common.domain.Role;
import com.epam.news.common.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public final class UserDetailsImpl implements UserDetails {

    private User user;
    private List<GrantedAuthority> authorities = new ArrayList<>();


    public UserDetailsImpl(User userData, Role userRole) {
        user = userData;
        authorities.add(new SimpleGrantedAuthority(userRole.getRoleName()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLogin();
    }

    public User getUser() {
        return user;
    }

    public String getFullName() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

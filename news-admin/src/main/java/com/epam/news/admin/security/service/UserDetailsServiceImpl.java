package com.epam.news.admin.security.service;

import com.epam.news.admin.security.domain.UserDetailsImpl;
import com.epam.news.common.domain.Role;
import com.epam.news.common.domain.User;
import com.epam.news.common.exception.ServiceException;
import com.epam.news.common.service.RoleService;
import com.epam.news.common.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

@Service
public final class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        try {
            User user = userService.getUserByLogin(login);
            Role role = roleService.find(user.getUserId());

            return new UserDetailsImpl(user, role);

        } catch (ServiceException e) {
            throw new UsernameNotFoundException("User details not found with this username: " + login, e);
        }
    }
}

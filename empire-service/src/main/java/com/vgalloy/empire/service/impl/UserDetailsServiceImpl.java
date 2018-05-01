package com.vgalloy.empire.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vgalloy.empire.service.model.User;
import com.vgalloy.empire.service.spi.dao.UserDao;

/**
 * Created by Vincent Galloy on 30/08/17.
 *
 * @author Vincent Galloy
 */
@Service
class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDao userDao;

    /**
     * Constructor.
     *
     * @param userDao the user dao
     */
    UserDetailsServiceImpl(final UserDao userDao) {
        this.userDao = Objects.requireNonNull(userDao);
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        Objects.requireNonNull(username);

        final List<User> users = userDao.getByLogin(username).stream()
            .filter(user -> user.getLogin().equals(username))
            .collect(Collectors.toList());

        if (users.size() != 1) {
            throw new UsernameNotFoundException("");
        }
        final User user = users.get(0);

        return new org.springframework.security.core.userdetails.User(user.getPassword(), user.getPassword(), Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}

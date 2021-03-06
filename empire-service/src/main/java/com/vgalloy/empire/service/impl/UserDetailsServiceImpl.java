package com.vgalloy.empire.service.impl;

import com.vgalloy.empire.service.spi.dao.UserDao;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
  public UserDetails loadUserByUsername(final String username) {
    Objects.requireNonNull(username);

    final var users =
        userDao.getByLogin(username).stream()
            .filter(user -> user.getLogin().equals(username))
            .collect(Collectors.toList());

    if (users.isEmpty()) {
      throw new UsernameNotFoundException(username);
    }
    final var user = users.get(0);

    return new org.springframework.security.core.userdetails.User(
        user.getPassword(),
        user.getPassword(),
        Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
  }
}

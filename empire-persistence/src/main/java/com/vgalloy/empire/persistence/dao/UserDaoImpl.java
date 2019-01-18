package com.vgalloy.empire.persistence.dao;

import com.vgalloy.empire.service.model.User;
import com.vgalloy.empire.service.model.UserId;
import com.vgalloy.empire.service.spi.dao.UserDao;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

/**
 * Created by Vincent Galloy on 19/08/17.
 *
 * @author Vincent Galloy
 */
@Repository
final class UserDaoImpl implements UserDao {

  private final Map<UserId, User> users = new HashMap<>();

  @Override
  public Optional<User> getById(final UserId userId) {
    return Optional.ofNullable(users.get(userId));
  }

  @Override
  public User create(final String login, final String password) {
    final var user = User.of(login, password);
    users.put(user.getId(), user);
    return user;
  }

  @Override
  public Optional<User> findByLoginAndPassword(final String login, final String password) {
    return users.values().stream()
        .filter(user -> user.getLogin().equals(login) && user.getPassword().equals(password))
        .findFirst();
  }

  @Override
  public User update(final User user) {
    users.remove(user.getId());
    users.put(user.getId(), user);
    return user;
  }

  @Override
  public Collection<User> getAll() {
    return users.values();
  }

  @Override
  public List<User> getByLogin(final String login) {
    return getAll().stream()
        .filter(user -> user.getLogin().equals(login))
        .collect(Collectors.toList());
  }

  @Override
  public User remove(final UserId userId) {
    return users.remove(userId);
  }
}

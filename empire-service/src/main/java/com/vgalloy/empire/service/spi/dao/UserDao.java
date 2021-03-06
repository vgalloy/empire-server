package com.vgalloy.empire.service.spi.dao;

import com.vgalloy.empire.service.model.User;
import com.vgalloy.empire.service.model.UserId;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserDao {

  /**
   * Get user by id.
   *
   * @param userId the userId
   * @return the corresponding user
   */
  Optional<User> getById(UserId userId);

  /**
   * Create a user.
   *
   * @param login the login
   * @param password the password
   * @return the new user
   */
  User create(String login, String password);

  /**
   * Find the user by login and password.
   *
   * @param login the login
   * @param password the password
   * @return the corresponding user
   */
  Optional<User> findByLoginAndPassword(String login, String password);

  /**
   * User the user.
   *
   * @param user the user to update
   * @return the updated user
   */
  User update(User user);

  /**
   * Retrieve all users in data base.
   *
   * @return the users list
   */
  Collection<User> getAll();

  /**
   * Get users matching with login.
   *
   * @param login the login
   * @return the user
   */
  List<User> getByLogin(String login);

  /**
   * Remove a user.
   *
   * @param userId the user id
   * @return the removed user
   */
  User remove(UserId userId);
}

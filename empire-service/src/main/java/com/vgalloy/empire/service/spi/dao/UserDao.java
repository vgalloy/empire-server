package com.vgalloy.empire.service.spi.dao;

import java.util.List;
import java.util.Optional;

import com.vgalloy.empire.service.model.User;
import com.vgalloy.empire.service.model.UserId;

public interface UserDao {

    /**
     * Get user by id.
     *
     * @param userId the userId
     * @return the corresponding user
     */
    User getById(UserId userId);

    /**
     * Create a user.
     *
     * @param login    the login
     * @param password the password
     * @return the new user
     */
    User create(String login, String password);

    /**
     * Find the user by login and password.
     *
     * @param login    the login
     * @param password the password
     * @return the corresponding user
     */
    Optional<User> findByLoginAndPassword(String login, String password);

    /**
     * User the user.
     *
     * @param user the user to update
     */
    void update(User user);

    /**
     * Retrieve all users in data base.
     *
     * @return the users list
     */
    List<User> getAll();

    /**
     * Get users matching with login.
     *
     * @param login the login
     * @return the user
     */
    List<User> getByLogin(String login);
}

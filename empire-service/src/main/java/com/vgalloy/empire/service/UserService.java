package com.vgalloy.empire.service;

import java.util.Collection;
import java.util.Optional;

import com.vgalloy.empire.service.model.User;
import com.vgalloy.empire.service.model.UserId;

/**
 * Create by Vincent Galloy on 02/08/2017.
 *
 * @author Vincent Galloy
 */
public interface UserService {

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
    Collection<User> getAll();

    /**
     * Remove a user.
     *
     * @param userId the user id
     * @return the removed user
     */
    User remove(UserId userId);
}

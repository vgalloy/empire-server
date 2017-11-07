package com.vgalloy.empire.service.model;

import java.util.Objects;

/**
 * Create by Vincent Galloy on 02/08/2017.
 *
 * @author Vincent Galloy
 */
public final class User {

    private final UserId id;
    private final String login;
    private final String password;

    /**
     * Constructor.
     * Private to avoid non managed instantiation
     *
     * @param id       the user id (must be unique)
     * @param login    the login
     * @param password the password
     */
    private User(UserId id, String login, String password) {
        this.id = Objects.requireNonNull(id);
        this.login = Objects.requireNonNull(login);
        this.password = Objects.requireNonNull(password);
    }

    /**
     * Build a {@link User}.
     *
     * @param login    the login
     * @param password the password
     * @return the user
     */
    public static User of(String login, String password) {
        UserId id = UserId.newInstance();
        Objects.requireNonNull(login);
        Objects.requireNonNull(password);

        return new User(id, login, password);
    }

    /**
     * Build a {@link User}.
     *
     * @param userId   the user id
     * @param login    the login
     * @param password the password
     * @return the user
     */
    public static User of(UserId userId, String login, String password) {
        Objects.requireNonNull(userId);
        Objects.requireNonNull(login);
        Objects.requireNonNull(password);

        return new User(userId, login, password);
    }

    public UserId getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}

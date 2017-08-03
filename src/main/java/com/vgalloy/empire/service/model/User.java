package com.vgalloy.empire.service.model;

import java.util.Objects;

/**
 * Create by Vincent Galloy on 02/08/2017.
 *
 * @author Vincent Galloy
 */
public final class User {

	private final String id;
	private final String login;
	private final String password;

	public User(String id, String login, String password) {
		this.id = Objects.requireNonNull(id);
		this.login = Objects.requireNonNull(login);
		this.password = Objects.requireNonNull(password);
	}

	public String getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}
}

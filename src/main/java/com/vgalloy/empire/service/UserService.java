package com.vgalloy.empire.service;

import java.util.List;
import java.util.Optional;

import com.vgalloy.empire.service.model.User;

/**
 * Create by Vincent Galloy on 02/08/2017.
 *
 * @author Vincent Galloy
 */
public interface UserService {

	User getById(String userId);

	User create(String login, String password);

	Optional<User> findByLoginAndPassword(String login, String password);

	void update(User user);

	List<User> getAll();
}

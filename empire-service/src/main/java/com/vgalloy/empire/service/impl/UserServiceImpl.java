package com.vgalloy.empire.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.vgalloy.empire.service.UserService;
import com.vgalloy.empire.service.model.User;

/**
 * Create by Vincent Galloy on 02/08/2017.
 *
 * @author Vincent Galloy
 */
@Component
final class UserServiceImpl implements UserService {

    private final List<User> users = new ArrayList<>();

    @Override
    public User getById(String userId) {
        return users.stream()
            .filter(user -> user.getId().equals(userId))
            .findFirst()
            .orElseThrow(() -> new IllegalStateException("No user found for the given id"));
    }

    @Override
    public User create(String login, String password) {
        User user = new User(UUID.randomUUID().toString(), login, password);
        users.add(user);
        return user;
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        return users.stream()
            .filter(user -> user.getLogin().equals(login) && user.getPassword().equals(password))
            .findFirst();
    }

    @Override
    public void update(User user) {
        User userToUpdate = getById(user.getId());
        users.remove(userToUpdate);
        users.add(user);
    }

    @Override
    public List<User> getAll() {
        return new ArrayList<>(users);
    }
}

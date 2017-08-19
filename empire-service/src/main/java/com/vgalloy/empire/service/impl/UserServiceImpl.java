package com.vgalloy.empire.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.vgalloy.empire.service.UserService;
import com.vgalloy.empire.service.model.User;
import com.vgalloy.empire.service.spi.dao.UserDao;

/**
 * Create by Vincent Galloy on 02/08/2017.
 *
 * @author Vincent Galloy
 */
@Component
final class UserServiceImpl implements UserService {

    private final UserDao userDao;

    /**
     * Constructor.
     *
     * @param userDao the user service Dao
     */
    UserServiceImpl(UserDao userDao) {
        this.userDao = Objects.requireNonNull(userDao);
    }

    @Override
    public User getById(String userId) {
        return userDao.getById(userId);
    }

    @Override
    public User create(String login, String password) {
        return userDao.create(login, password);
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        return userDao.findByLoginAndPassword(login, password);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }
}

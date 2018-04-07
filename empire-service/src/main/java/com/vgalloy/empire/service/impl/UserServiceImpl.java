package com.vgalloy.empire.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import com.vgalloy.empire.common.LogLevel;
import com.vgalloy.empire.common.executiontime.ExecutionTimeLog;
import com.vgalloy.empire.common.log.FullLog;
import com.vgalloy.empire.service.UserService;
import com.vgalloy.empire.service.model.User;
import com.vgalloy.empire.service.model.UserId;
import com.vgalloy.empire.service.spi.dao.UserDao;

/**
 * Create by Vincent Galloy on 02/08/2017.
 *
 * @author Vincent Galloy
 */
@FullLog
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
    @PreAuthorize("hasRole('USER')")
    public User getById(UserId userId) {
        return userDao.getById(userId);
    }

    @Override
    @FullLog(LogLevel.TRACE)
    @ExecutionTimeLog
    public User create(String login, String password) {
        return userDao.create(login, password);
    }

    @Override
    @FullLog(LogLevel.TRACE)
    public Optional<User> findByLoginAndPassword(String login, String password) {
        return userDao.findByLoginAndPassword(login, password);
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }
}

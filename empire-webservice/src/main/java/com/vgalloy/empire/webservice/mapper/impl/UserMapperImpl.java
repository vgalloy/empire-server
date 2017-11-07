package com.vgalloy.empire.webservice.mapper.impl;

import org.springframework.stereotype.Component;

import com.vgalloy.empire.service.model.User;
import com.vgalloy.empire.service.model.UserId;
import com.vgalloy.empire.webservice.dto.UserDto;
import com.vgalloy.empire.webservice.dto.UserIdDto;
import com.vgalloy.empire.webservice.exception.UserInputException;
import com.vgalloy.empire.webservice.mapper.UserIdMapper;
import com.vgalloy.empire.webservice.mapper.UserMapper;

/**
 * Create by Vincent Galloy on 02/08/2017.
 *
 * @author Vincent Galloy
 */
@Component
final class UserMapperImpl implements UserMapper {

    private final UserIdMapper userIdMapper;

    /**
     * Constructor.
     *
     * @param userIdMapper the user id mapper
     */
    UserMapperImpl(UserIdMapper userIdMapper) {
        this.userIdMapper = userIdMapper;
    }

    @Override
    public UserDto map(User user) {
        UserDto result = new UserDto();
        result.setLogin(user.getLogin());
        result.setPassword(user.getPassword());
        return result;
    }

    @Override
    public User unmap(UserIdDto userIdDto, UserDto userDto) {
        UserInputException.requireNonNull(userIdDto, "User id can't be null");
        UserInputException.requireNonNullNonEmptyNonBlank(userDto.getLogin(), "Invalid user login");
        UserInputException.requireNonNullNonEmptyNonBlank(userDto.getPassword(), "Invalid user password");

        UserId userId = userIdMapper.unmap(userIdDto);
        return User.of(userId, userDto.getLogin(), userDto.getPassword());
    }
}

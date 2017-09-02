package com.vgalloy.empire.webservice.mapper.impl;

import org.springframework.stereotype.Component;

import com.vgalloy.empire.service.model.UserId;
import com.vgalloy.empire.webservice.dto.UserIdDto;
import com.vgalloy.empire.webservice.exception.UserInputException;
import com.vgalloy.empire.webservice.mapper.UserIdMapper;

/**
 * Create by Vincent Galloy on 02/08/2017.
 *
 * @author Vincent Galloy
 */
@Component
final class UserIdMapperImpl implements UserIdMapper {

    @Override
    public UserIdDto map(UserId userId) {
        UserIdDto result = new UserIdDto();
        result.setUserId(userId.getId());
        return result;
    }

    @Override
    public UserId unmap(UserIdDto dto) {
        UserInputException.requireNonNull(dto, "Empire id can't be null");
        UserInputException.requireNonNullNonEmptyNonBlank(dto.getUserId(), "Invalid user id");

        return UserId.of(dto.getUserId());
    }
}

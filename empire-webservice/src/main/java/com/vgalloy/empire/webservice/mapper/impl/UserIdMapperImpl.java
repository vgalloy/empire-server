package com.vgalloy.empire.webservice.mapper.impl;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import com.vgalloy.empire.service.model.UserId;
import com.vgalloy.empire.webservice.dto.UserIdDto;
import com.vgalloy.empire.webservice.mapper.UserIdMapper;

/**
 * Create by Vincent Galloy on 02/08/2017.
 *
 * @author Vincent Galloy
 */
@Validated
@Component
class UserIdMapperImpl implements UserIdMapper {

    @Override
    public UserIdDto map(final UserId userId) {
        final UserIdDto result = new UserIdDto();
        result.setUserId(userId.getId());
        return result;
    }

    @Override
    public UserId unmap(final UserIdDto dto) {
        return UserId.of(dto.getUserId());
    }
}

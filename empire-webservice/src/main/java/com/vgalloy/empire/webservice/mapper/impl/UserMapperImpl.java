package com.vgalloy.empire.webservice.mapper.impl;

import com.vgalloy.empire.service.model.User;
import com.vgalloy.empire.service.model.UserId;
import com.vgalloy.empire.webservice.dto.UserDto;
import com.vgalloy.empire.webservice.mapper.UserMapper;
import java.util.UUID;
import org.springframework.stereotype.Component;

/**
 * Create by Vincent Galloy on 02/08/2017.
 *
 * @author Vincent Galloy
 */
@Component
final class UserMapperImpl implements UserMapper {

  @Override
  public UserDto map(final User user) {
    final var result = new UserDto();
    result.setLogin(user.getLogin());
    result.setPassword(user.getPassword());
    return result;
  }

  @Override
  public User unmap(final UUID userIdDto, final UserDto userDto) {
    final var userId = UserId.of(userIdDto);
    return User.of(userId, userDto.getLogin(), userDto.getPassword());
  }
}

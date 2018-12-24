package com.vgalloy.empire.webservice.mapper;

import com.vgalloy.empire.service.model.User;
import com.vgalloy.empire.webservice.dto.UserDto;
import java.util.UUID;

/**
 * Create by Vincent Galloy on 02/08/2017.
 *
 * @author Vincent Galloy
 */
public interface UserMapper extends Mapper<User, UserDto> {

  /**
   * Create a User from request object.
   *
   * @param userIdDto the user id dto
   * @param userDto the user dto
   * @return the User
   */
  User unmap(UUID userIdDto, UserDto userDto);
}

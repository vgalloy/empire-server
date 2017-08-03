package com.vgalloy.empire.webservice.mapper;

import com.vgalloy.empire.service.model.User;
import com.vgalloy.empire.webservice.dto.UserDto;

/**
 * Create by Vincent Galloy on 02/08/2017.
 *
 * @author Vincent Galloy
 */
public interface UserMapper extends Mapper<User, UserDto> {

	User unmap(String userId, UserDto userDto);
}

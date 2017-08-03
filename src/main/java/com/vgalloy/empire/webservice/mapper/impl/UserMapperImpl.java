package com.vgalloy.empire.webservice.mapper.impl;

import org.springframework.stereotype.Component;

import com.vgalloy.empire.service.model.User;
import com.vgalloy.empire.webservice.dto.UserDto;
import com.vgalloy.empire.webservice.mapper.UserMapper;

/**
 * Create by Vincent Galloy on 02/08/2017.
 *
 * @author Vincent Galloy
 */
@Component
final class UserMapperImpl implements UserMapper {

	@Override
	public UserDto map(User user) {
		UserDto result = new UserDto();
		result.setLogin(user.getLogin());
		result.setPassword(user.getPassword());
		return result;
	}

	@Override
	public User unmap(String userId, UserDto userDto) {
		return new User(userId, userDto.getLogin(), userDto.getPassword());
	}
}

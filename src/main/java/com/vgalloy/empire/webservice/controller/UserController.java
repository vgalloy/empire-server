package com.vgalloy.empire.webservice.controller;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vgalloy.empire.service.UserService;
import com.vgalloy.empire.service.model.User;
import com.vgalloy.empire.webservice.dto.UserDto;
import com.vgalloy.empire.webservice.exception.UserInputException;
import com.vgalloy.empire.webservice.mapper.UserMapper;

/**
 * Create by Vincent Galloy on 02/08/2017.
 *
 * @author Vincent Galloy
 */
@RestController
@RequestMapping("user")
public class UserController {

	private final UserService userService;
	private final UserMapper userMapper;

	public UserController(UserService userService, UserMapper userMapper) {
		this.userService = Objects.requireNonNull(userService);
		this.userMapper = Objects.requireNonNull(userMapper);
	}

	@GetMapping("{userId}")
	public UserDto getById(@PathVariable String userId) {
		UserInputException.requireNonNullNonEmptyNonBlank(userId, "Invalid user id");

		User user = userService.getById(userId);
		return userMapper.map(user);
	}

	@PostMapping
	public String create(@RequestBody UserDto userDto) {
		UserInputException.requireNonNull(userDto, "user can't be null");
		String login = UserInputException.requireNonNullNonEmptyNonBlank(userDto.getLogin(), "Invalid login");
		String password = UserInputException.requireNonNullNonEmptyNonBlank(userDto.getPassword(), "Invalid password");

		User user = userService.create(login, password);
		return user.getId();
	}

	@PutMapping("{userId}")
	public UserDto update(@PathVariable String userId, @RequestBody UserDto userDto) {
		UserInputException.requireNonNull(userDto, "user can't be null");

		User user = userMapper.unmap(userId, userDto);
		userService.update(user);
		return userMapper.map(user);
	}

	@GetMapping
	public List<String> getAll() {
		return userService.getAll().stream()
			.map(User::getId)
			.collect(Collectors.toList());
	}
}

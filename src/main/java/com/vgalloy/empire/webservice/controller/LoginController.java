package com.vgalloy.empire.webservice.controller;

import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vgalloy.empire.service.UserService;
import com.vgalloy.empire.service.model.User;
import com.vgalloy.empire.webservice.exception.NotFoundException;
import com.vgalloy.empire.webservice.exception.UserInputException;
import com.vgalloy.empire.webservice.request.LoginRequest;

@RestController
@RequestMapping("security")
public class LoginController {

	private static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	private final UserService userService;

	public LoginController(UserService userService) {
		this.userService = Objects.requireNonNull(userService);
	}

	@GetMapping("logout")
	public void logout() {

	}

	@PostMapping("login")
	public String login(@RequestBody LoginRequest loginRequest) {
		LOGGER.info("{}", loginRequest);
		UserInputException.requireNonNull(loginRequest, "loginRequest can't be null");
		String login = UserInputException.requireNonNullNonEmptyNonBlank(loginRequest.getLogin(), "Invalid login");
		String password = UserInputException.requireNonNullNonEmptyNonBlank(loginRequest.getPassword(), "Invalid password");

		Optional<User> user = userService.findByLoginAndPassword(login, password);

		return user.map(User::getId)
			.orElseThrow(NotFoundException::doesExist);
	}
}

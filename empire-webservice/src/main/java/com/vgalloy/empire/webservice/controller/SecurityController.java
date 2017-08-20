package com.vgalloy.empire.webservice.controller;

import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vgalloy.empire.service.UserService;
import com.vgalloy.empire.service.model.User;
import com.vgalloy.empire.webservice.exception.NotFoundException;
import com.vgalloy.empire.webservice.exception.UserInputException;
import com.vgalloy.empire.webservice.request.LoginRequest;

// TODO : remove
@RestController
@RequestMapping("security")
public class SecurityController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityController.class);

    private final UserService userService;

    /**
     * Constructor.
     *
     * @param userService the user service
     */
    public SecurityController(UserService userService) {
        this.userService = Objects.requireNonNull(userService);
    }

    /**
     * Login.
     *
     * @param loginRequest the login request
     * @return the user id
     */
    @PostMapping("login")
    public String login(@RequestBody LoginRequest loginRequest) {
        LOGGER.info("{}", loginRequest);
        UserInputException.requireNonNull(loginRequest, "loginRequest can't be null");
        String login = UserInputException.requireNonNullNonEmptyNonBlank(loginRequest.getLogin(), "Invalid login");
        String password = UserInputException.requireNonNullNonEmptyNonBlank(loginRequest.getPassword(), "Invalid password");

        Optional<User> user = userService.findByLoginAndPassword(login, password);

        return user.map(User::getId)
            .orElseThrow(NotFoundException::getInstance);
    }
}

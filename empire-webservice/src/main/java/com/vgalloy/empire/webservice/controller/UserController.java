package com.vgalloy.empire.webservice.controller;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vgalloy.empire.service.EmpireService;
import com.vgalloy.empire.service.UserService;
import com.vgalloy.empire.service.model.User;
import com.vgalloy.empire.webservice.dto.EmpireIdDto;
import com.vgalloy.empire.webservice.dto.UserDto;
import com.vgalloy.empire.webservice.exception.UserInputException;
import com.vgalloy.empire.webservice.mapper.EmpireIdMapper;
import com.vgalloy.empire.webservice.mapper.UserMapper;

/**
 * Create by Vincent Galloy on 02/08/2017.
 *
 * @author Vincent Galloy
 */
@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private final EmpireService empireService;
    private final EmpireIdMapper empireIdMapper;

    /**
     * Constructor.
     *  @param userService the user service
     * @param userMapper  the user mapper
     * @param empireService the empire service
     * @param empireIdMapper the empireId mapper
     */
    public UserController(UserService userService, UserMapper userMapper, EmpireService empireService, EmpireIdMapper empireIdMapper) {
        this.userService = Objects.requireNonNull(userService);
        this.userMapper = Objects.requireNonNull(userMapper);
        this.empireService = Objects.requireNonNull(empireService);
        this.empireIdMapper = Objects.requireNonNull(empireIdMapper);
    }

    /**
     * Get the user by id.
     *
     * @param userId the user id
     * @return the User
     */
    @GetMapping("{userId}")
    @PreAuthorize("ROLE_USER")
    public UserDto getById(@PathVariable String userId) {
        UserInputException.requireNonNullNonEmptyNonBlank(userId, "Invalid user id");

        User user = userService.getById(userId);
        return userMapper.map(user);
    }

    /**
     * Get the user by id.
     *
     * @param userId the user id
     * @return the User
     */
    @GetMapping("{userId}/empires")
    @PreAuthorize("ROLE_USER")
    public List<EmpireIdDto> getEmpiresByUser(@PathVariable String userId) {
        UserInputException.requireNonNullNonEmptyNonBlank(userId, "Invalid user id");

        return empireService.getEmpireIdByUserId(userId).stream()
            .map(empireIdMapper::map)
            .collect(Collectors.toList());
    }

    /**
     * Create a new user.
     *
     * @param userDto the user
     * @return the user id
     */
    @PostMapping
    @PreAuthorize("ROLE_USER")
    public String create(@RequestBody UserDto userDto) {
        UserInputException.requireNonNull(userDto, "User can't be null");
        String login = UserInputException.requireNonNullNonEmptyNonBlank(userDto.getLogin(), "Invalid login");
        String password = UserInputException.requireNonNullNonEmptyNonBlank(userDto.getPassword(), "Invalid password");

        User user = userService.create(login, password);
        return user.getId();
    }

    /**
     * Update the user.
     *
     * @param userId  the user id
     * @param userDto the user to update
     * @return the new user
     */
    @PutMapping("{userId}")
    public UserDto update(@PathVariable String userId, @RequestBody UserDto userDto) {
        UserInputException.requireNonNull(userDto, "user can't be null");

        User user = userMapper.unmap(userId, userDto);
        userService.update(user);
        return userMapper.map(user);
    }

    /**
     * Get all user id.
     *
     * @return the list internalCreate user id
     */
    @GetMapping
    public List<String> getAll() {
        return userService.getAll().stream()
            .map(User::getId)
            .collect(Collectors.toList());
    }
}

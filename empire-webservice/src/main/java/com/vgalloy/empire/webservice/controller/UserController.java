package com.vgalloy.empire.webservice.controller;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vgalloy.empire.common.nullable.NotNullApi;
import com.vgalloy.empire.service.EmpireService;
import com.vgalloy.empire.service.UserService;
import com.vgalloy.empire.service.model.User;
import com.vgalloy.empire.service.model.UserId;
import com.vgalloy.empire.webservice.dto.EmpireIdDto;
import com.vgalloy.empire.webservice.dto.UserDto;
import com.vgalloy.empire.webservice.exception.NotFoundResourceException;
import com.vgalloy.empire.webservice.mapper.EmpireIdMapper;
import com.vgalloy.empire.webservice.mapper.UserMapper;
import com.vgalloy.empire.webservice.resource.DataResource;
import com.vgalloy.empire.webservice.resource.LinkWithMethod;

/**
 * Create by Vincent Galloy on 02/08/2017.
 *
 * @author Vincent Galloy
 */
@NotNullApi
@Validated
@RestController
@RequestMapping("users")
public class UserController {

    private static final String USER_ID_MUST_BE_NOT_NULL = "User id can't be null";

    private final UserService userService;
    private final UserMapper userMapper;
    private final EmpireService empireService;
    private final EmpireIdMapper empireIdMapper;

    /**
     * Constructor.
     *
     * @param userService    the user service
     * @param userMapper     the user mapper
     * @param empireService  the empire service
     * @param empireIdMapper the empireId mapper
     */
    public UserController(final UserService userService, final UserMapper userMapper, final EmpireService empireService, final EmpireIdMapper empireIdMapper) {
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
    public DataResource<UserDto> getById(@PathVariable @Valid @NotNull(message = USER_ID_MUST_BE_NOT_NULL) final UUID userId) {
        final User user = userService.getById(UserId.of(userId)).orElseThrow(() -> new NotFoundResourceException(userId));
        final UserDto userDto = userMapper.map(user);
        return buildResource(userId, userDto);
    }

    /**
     * Get the user by id.
     *
     * @param userId the user id
     * @return the User
     */
    @GetMapping("{userId}/empires")
    public List<EmpireIdDto> getEmpiresByUser(@PathVariable @Valid @NotNull(message = USER_ID_MUST_BE_NOT_NULL) final UUID userId) {
        return empireService.getEmpireIdByUserId(UserId.of(userId)).stream()
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
    public DataResource<UserDto> create(@RequestBody @Valid @NotNull(message = USER_ID_MUST_BE_NOT_NULL) final UserDto userDto) {
        final User user = userService.create(userDto.getLogin(), userDto.getPassword());
        final UUID userId = user.getId().getId();
        return buildResource(userId, userDto);
    }

    /**
     * Update the user.
     *
     * @param userId  the user id
     * @param userDto the user to update
     * @return the new user
     */
    @PutMapping("{userId}")
    public DataResource<UserDto> update(@PathVariable @Valid @NotNull(message = USER_ID_MUST_BE_NOT_NULL) final UUID userId, @RequestBody @Valid @NotNull(message = "User can't be null") final UserDto userDto) {
        final User user = userMapper.unmap(userId, userDto);
        userService.update(user);
        return buildResource(userId, userDto);
    }

    /**
     * Delete the user.
     *
     * @param userId the user id
     * @return the new user
     */
    @DeleteMapping("{userId}")
    public DataResource<UserDto> delete(@PathVariable @Valid @NotNull(message = USER_ID_MUST_BE_NOT_NULL) final UUID userId) {
        final User user = userService.remove(UserId.of(userId));
        final UserDto userDto = userMapper.map(user);
        return new DataResource<>(userId, userDto);
    }

    /**
     * Get all user id.
     *
     * @return the list internalCreate user id
     */
    @GetMapping
    public List<UUID> getAll() {
        return userService.getAll().stream()
            .map(User::getId)
            .map(UserId::getId)
            .collect(Collectors.toList());
    }

    /**
     * Build the output resource.
     *
     * @param userId  the user id
     * @param userDto the user dto
     * @return the wrapper
     */
    private DataResource<UserDto> buildResource(final UUID userId, final UserDto userDto) {
        final DataResource<UserDto> resource = new DataResource<>(userId, userDto);
        resource.add(LinkWithMethod.linkTo(ControllerLinkBuilder.methodOn(UserController.class).getById(userId)).withSelfRel());
        resource.add(LinkWithMethod.linkTo(ControllerLinkBuilder.methodOn(UserController.class).update(userId, userDto)));
        resource.add(LinkWithMethod.linkTo(ControllerLinkBuilder.methodOn(UserController.class).delete(userId)));
        return resource;
    }
}

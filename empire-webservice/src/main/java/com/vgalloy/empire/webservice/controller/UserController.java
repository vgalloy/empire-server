package com.vgalloy.empire.webservice.controller;

import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.hateoas.MediaTypes;
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
import com.vgalloy.empire.service.model.EmpireId;
import com.vgalloy.empire.service.model.UserId;
import com.vgalloy.empire.webservice.dto.UserDto;
import com.vgalloy.empire.webservice.exception.NotFoundResourceException;
import com.vgalloy.empire.webservice.mapper.UserMapper;
import com.vgalloy.empire.webservice.resource.LinkWithMethod;
import com.vgalloy.empire.webservice.resource.ResourceData;
import com.vgalloy.empire.webservice.resource.ResourceList;

/**
 * Create by Vincent Galloy on 02/08/2017.
 *
 * @author Vincent Galloy
 */
@NotNullApi
@Validated
@RestController
@RequestMapping(value = "users", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class UserController {

    private static final String USER_ID_MUST_BE_NOT_NULL = "User id can't be null";

    private final UserService userService;
    private final UserMapper userMapper;
    private final EmpireService empireService;

    /**
     * Constructor.
     *
     * @param userService   the user service
     * @param userMapper    the user mapper
     * @param empireService the empire service
     */
    public UserController(final UserService userService, final UserMapper userMapper, final EmpireService empireService) {
        this.userService = Objects.requireNonNull(userService);
        this.userMapper = Objects.requireNonNull(userMapper);
        this.empireService = Objects.requireNonNull(empireService);
    }

    /**
     * Get the user by id.
     *
     * @param userId the user id
     * @return the User
     */
    @GetMapping("{userId}")
    public ResourceData<UserDto> getById(@PathVariable @Valid @NotNull(message = USER_ID_MUST_BE_NOT_NULL) final UUID userId) {
        final var user = userService.getById(UserId.of(userId)).orElseThrow(() -> new NotFoundResourceException(userId));
        final var userDto = userMapper.map(user);
        return buildResource(userId, userDto);
    }

    /**
     * Get the user by id.
     *
     * @param userId the user id
     * @return the User
     */
    @GetMapping("{userId}/empires")
    public ResourceList<UUID> getEmpiresByUser(@PathVariable @Valid @NotNull(message = USER_ID_MUST_BE_NOT_NULL) final UUID userId) {
        final var ids = empireService.getEmpireIdByUserId(UserId.of(userId)).stream()
            .map(EmpireId::getId)
            .collect(Collectors.toList());
        return new ResourceList<>(ids);
    }

    /**
     * Create a new user.
     *
     * @param userDto the user
     * @return the user id
     */
    @PostMapping
    public ResourceData<UserDto> create(@RequestBody @Valid @NotNull(message = USER_ID_MUST_BE_NOT_NULL) final UserDto userDto) {
        final var user = userService.create(userDto.getLogin(), userDto.getPassword());
        final var userId = user.getId().getId();
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
    public ResourceData<UserDto> update(@PathVariable @Valid @NotNull(message = USER_ID_MUST_BE_NOT_NULL) final UUID userId, @RequestBody @Valid @NotNull(message = "User can't be null") final UserDto userDto) {
        final var user = userMapper.unmap(userId, userDto);
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
    public ResourceData<UserDto> delete(@PathVariable @Valid @NotNull(message = USER_ID_MUST_BE_NOT_NULL) final UUID userId) {
        final var user = userService.remove(UserId.of(userId));
        final var userDto = userMapper.map(user);
        return new ResourceData<>(userId, userDto);
    }

    /**
     * Get all user id.
     *
     * @return the list internalCreate user id
     */
    @GetMapping
    public ResourceList<UserDto> getAll() {
        final var users = userService.getAll().stream()
            .map(userMapper::map)
            .collect(Collectors.toList());
        return new ResourceList<>(users);
    }

    /**
     * Build the output resource.
     *
     * @param userId  the user id
     * @param userDto the user dto
     * @return the wrapper
     */
    private ResourceData<UserDto> buildResource(final UUID userId, final UserDto userDto) {
        final var resource = new ResourceData<>(userId, userDto);
        resource.add(LinkWithMethod.linkTo(ControllerLinkBuilder.methodOn(UserController.class).getById(userId)).withSelfRel());
        resource.add(LinkWithMethod.linkTo(ControllerLinkBuilder.methodOn(UserController.class).update(userId, userDto)));
        resource.add(LinkWithMethod.linkTo(ControllerLinkBuilder.methodOn(UserController.class).delete(userId)));
        resource.add(LinkWithMethod.linkTo(ControllerLinkBuilder.methodOn(UserController.class).getEmpiresByUser(userId)));
        return resource;
    }
}

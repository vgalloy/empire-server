package com.vgalloy.empire.webservice.controller;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vgalloy.empire.common.nullable.NotNullApi;
import com.vgalloy.empire.service.EmpireService;
import com.vgalloy.empire.service.model.Empire;
import com.vgalloy.empire.service.model.EmpireId;
import com.vgalloy.empire.service.model.order.OrderType;
import com.vgalloy.empire.service.spi.dao.EmpireDao;
import com.vgalloy.empire.webservice.dto.EmpireDto;
import com.vgalloy.empire.webservice.mapper.EmpireMapper;
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
@RequestMapping(value = "empires", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class EmpireController {

    private final EmpireMapper empireMapper;
    private final EmpireService empireService;
    private final EmpireDao empireDao;

    /**
     * Constructor.
     *
     * @param empireMapper   the empireMapper
     * @param empireService  the empireService
     * @param empireDao      the empireDao
     */
    public EmpireController(final EmpireMapper empireMapper, final EmpireService empireService, final EmpireDao empireDao) {
        this.empireMapper = Objects.requireNonNull(empireMapper);
        this.empireService = Objects.requireNonNull(empireService);
        this.empireDao = Objects.requireNonNull(empireDao);
    }

    /**
     * Get the list internalCreate Order available.
     *
     * @return the list internalCreate order
     */
    @GetMapping("ordersTypes")
    public List<OrderType> getOrdersTypes() {
        return Stream.of(OrderType.values()).collect(Collectors.toList());
    }

    /**
     * Get Empire by id.
     *
     * @param empireId the empireId
     * @return the empire
     */
    @GetMapping("{empireId}")
    public DataResource<EmpireDto> getById(@PathVariable @Valid @NotNull(message = "Empire id can't be null") final UUID empireId) {
        // EXTRACT
        final EmpireId id = EmpireId.of(empireId);
        // DO
        final Empire empire = empireService.getEmpireById(id);
        // MAP
        final EmpireDto empireDto = empireMapper.map(empire);

        final DataResource<EmpireDto> resource = new DataResource<>(empireId, empireDto);
        resource.add(LinkWithMethod.linkTo(ControllerLinkBuilder.methodOn(EmpireController.class).getById(empireId)).withSelfRel());
        return resource;
    }

    /**
     * Compute next round for the given empire.
     *
     * @param empireId the empireId
     */
    @PutMapping("{empireId}/nextRound")
    public void nextRound(@PathVariable @Valid @NotNull(message = "Empire id can't be null") final UUID empireId) {
        // EXTRACT
        final EmpireId id = EmpireId.of(empireId);
        // DO
        final Empire empire = empireService.getEmpireById(id);
        final Empire newEmpire = empireService.computeNextRound(empire);
        this.empireDao.update(newEmpire);
    }

    /**
     * Update the order.
     *
     * @param empireId the empire id
     * @param orders   the new orders
     */
    @PatchMapping("{empireId}/order")
    public void updateOrder(@PathVariable @Valid @NotNull(message = "Empire id can't be null") final UUID empireId, @Valid @NotNull @RequestBody final Map<OrderType, Long> orders) {
        // EXTRACT
        final EmpireId id = EmpireId.of(empireId);
        // DO
        final Empire empire = empireService.getEmpireById(id);
        empireService.updateOrders(empire, orders);
    }
}

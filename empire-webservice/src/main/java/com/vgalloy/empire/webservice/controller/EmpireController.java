package com.vgalloy.empire.webservice.controller;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.hateoas.MediaTypes;
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
import com.vgalloy.empire.service.model.EmpireId;
import com.vgalloy.empire.service.model.order.OrderType;
import com.vgalloy.empire.service.spi.dao.EmpireDao;
import com.vgalloy.empire.webservice.dto.EmpireDto;
import com.vgalloy.empire.webservice.mapper.EmpireMapper;
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
@RequestMapping(value = "empires", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class EmpireController {

    private final EmpireMapper empireMapper;
    private final EmpireService empireService;
    private final EmpireDao empireDao;
    private final ControllerLinker controllerLinker;

    /**
     * Constructor.
     *
     * @param empireMapper     the empireMapper
     * @param empireService    the empireService
     * @param empireDao        the empireDao
     * @param controllerLinker the linker
     */
    public EmpireController(final EmpireMapper empireMapper, final EmpireService empireService, final EmpireDao empireDao, final ControllerLinker controllerLinker) {
        this.empireMapper = Objects.requireNonNull(empireMapper);
        this.empireService = Objects.requireNonNull(empireService);
        this.empireDao = Objects.requireNonNull(empireDao);
        this.controllerLinker = Objects.requireNonNull(controllerLinker);
    }

    /**
     * Get the list internalCreate Order available.
     *
     * @return the list internalCreate order
     */
    @GetMapping("ordersTypes")
    public ResourceList<OrderType> getOrdersTypes() {
        return new ResourceList<>(Stream.of(OrderType.values()).collect(Collectors.toList()));
    }

    /**
     * Get Empire by id.
     *
     * @param empireId the empireId
     * @return the empire
     */
    @GetMapping("{empireId}")
    public ResourceData<EmpireDto> getById(@PathVariable @Valid @NotNull(message = "Empire id can't be null") final UUID empireId) {
        // EXTRACT
        final var id = EmpireId.of(empireId);
        // DO
        final var empire = empireService.getEmpireById(id);
        // MAP
        final var empireDto = empireMapper.map(empire);

        return buildResource(empireId, empireDto);
    }

    /**
     * Compute next round for the given empire.
     *
     * @param empireId the empireId
     * @return the updated empire
     */
    @PutMapping("{empireId}/nextRound")
    public ResourceData<EmpireDto> nextRound(@PathVariable @Valid @NotNull(message = "Empire id can't be null") final UUID empireId) {
        // EXTRACT
        final var id = EmpireId.of(empireId);
        // DO
        final var empire = empireService.getEmpireById(id);
        final var newEmpire = empireService.computeNextRound(empire);
        final var empireDto = empireMapper.map(this.empireDao.update(newEmpire));
        return buildResource(empireId, empireDto);
    }

    /**
     * Update the order.
     *
     * @param empireId the empire id
     * @param orders   the new orders
     * @return the updated empire
     */
    @PatchMapping("{empireId}/order")
    public ResourceData<EmpireDto> updateOrder(@PathVariable @Valid @NotNull(message = "Empire id can't be null") final UUID empireId, @Valid @NotNull @RequestBody final Map<OrderType, Long> orders) {
        // EXTRACT
        final var id = EmpireId.of(empireId);
        // DO
        final var empire = empireService.getEmpireById(id);
        final var empireDto = empireMapper.map(empireService.updateOrders(empire, orders));

        return buildResource(empireId, empireDto);
    }

    /**
     * Build the output resource.
     *
     * @param empireId  the empire id
     * @param empireDto the empire dto
     * @return the wrapper
     */
    private ResourceData<EmpireDto> buildResource(final UUID empireId, final EmpireDto empireDto) {
        final var resource = new ResourceData<>(empireId, empireDto);
        controllerLinker.addEmpireLink(resource);
        return resource;
    }
}

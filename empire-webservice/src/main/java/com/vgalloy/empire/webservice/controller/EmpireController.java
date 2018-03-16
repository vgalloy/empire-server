package com.vgalloy.empire.webservice.controller;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vgalloy.empire.service.EmpireService;
import com.vgalloy.empire.service.model.Empire;
import com.vgalloy.empire.service.model.EmpireId;
import com.vgalloy.empire.service.model.order.OrderType;
import com.vgalloy.empire.service.spi.dao.EmpireDao;
import com.vgalloy.empire.webservice.dto.EmpireDto;
import com.vgalloy.empire.webservice.dto.EmpireIdDto;
import com.vgalloy.empire.webservice.mapper.EmpireIdMapper;
import com.vgalloy.empire.webservice.mapper.EmpireMapper;

/**
 * Create by Vincent Galloy on 02/08/2017.
 *
 * @author Vincent Galloy
 */
@Validated
@RestController
@RequestMapping("empires")
public class EmpireController {

    private final EmpireMapper empireMapper;
    private final EmpireIdMapper empireIdMapper;
    private final EmpireService empireService;
    private final EmpireDao empireDao;

    /**
     * Constructor.
     *
     * @param empireMapper   the empireMapper
     * @param empireIdMapper the empireIdMapper
     * @param empireService  the empireService
     * @param empireDao      the empireDao
     */
    public EmpireController(EmpireMapper empireMapper, EmpireIdMapper empireIdMapper, EmpireService empireService, EmpireDao empireDao) {
        this.empireMapper = Objects.requireNonNull(empireMapper);
        this.empireIdMapper = Objects.requireNonNull(empireIdMapper);
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
    public EmpireDto getById(@PathVariable @Valid @NotNull EmpireIdDto empireId) {
        // EXTRACT
        EmpireId id = this.empireIdMapper.unmap(empireId);
        // DO
        Empire empire = empireService.getEmpireById(id);
        // MAP
        return empireMapper.map(empire);
    }

    /**
     * Compute next round for the given empire.
     *
     * @param empireId the empireId
     */
    @PutMapping("{empireId}/nextRound")
    public void nextRound(@PathVariable @Valid @NotNull EmpireIdDto empireId) {
        // EXTRACT
        EmpireId id = this.empireIdMapper.unmap(empireId);
        // DO
        Empire empire = empireService.getEmpireById(id);
        Empire newEmpire = empireService.computeNextRound(empire);
        this.empireDao.update(newEmpire);
    }

    /**
     * Update the order.
     *
     * @param empireId the empire id
     * @param orders   the new orders
     */
    @PatchMapping("{empireId}/order")
    public void updateOrder(@PathVariable @Valid @NotNull EmpireIdDto empireId, @Valid @NotNull @RequestBody Map<OrderType, Long> orders) {
        // EXTRACT
        EmpireId id = this.empireIdMapper.unmap(empireId);
        // DO
        Empire empire = empireService.getEmpireById(id);
        empireService.updateOrders(empire, orders);
    }
}

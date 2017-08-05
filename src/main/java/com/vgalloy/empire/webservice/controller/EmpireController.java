package com.vgalloy.empire.webservice.controller;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vgalloy.empire.dao.EmpireDao;
import com.vgalloy.empire.service.EmpireService;
import com.vgalloy.empire.service.model.Empire;
import com.vgalloy.empire.service.model.EmpireId;
import com.vgalloy.empire.service.model.order.OrderType;
import com.vgalloy.empire.webservice.dto.EmpireDto;
import com.vgalloy.empire.webservice.mapper.EmpireIdMapper;
import com.vgalloy.empire.webservice.mapper.EmpireMapper;

/**
 * Create by Vincent Galloy on 02/08/2017.
 *
 * @author Vincent Galloy
 */
@RestController
@RequestMapping("empire")
public class EmpireController {

	private final EmpireMapper empireMapper;
	private final EmpireIdMapper empireIdMapper;
	private final EmpireService empireService;
	private final EmpireDao empireDao;

	public EmpireController(EmpireMapper empireMapper, EmpireIdMapper empireIdMapper, EmpireService empireService, EmpireDao empireDao) {
		this.empireMapper = Objects.requireNonNull(empireMapper);
		this.empireIdMapper = Objects.requireNonNull(empireIdMapper);
		this.empireService = Objects.requireNonNull(empireService);
		this.empireDao = Objects.requireNonNull(empireDao);
	}

	@GetMapping("/ordersType")
	public List<OrderType> getById() {
		return Stream.of(OrderType.values()).collect(Collectors.toList());
	}

	@GetMapping("/{empireId}")
	public EmpireDto getById(@PathVariable String empireId) {
		// EXTRACT
		EmpireId id = this.empireIdMapper.unmap(empireId);
		// DO
		Empire empire = empireService.getEmpireById(id);
		// MAP
		return empireMapper.map(empire);
	}

	@PutMapping("/{empireId}/nextRound")
	public void nextRound(@PathVariable String empireId) {
		// EXTRACT
		EmpireId id = this.empireIdMapper.unmap(empireId);
		// DO
		Empire empire = empireService.getEmpireById(id);
		Empire newEmpire = empireService.computeNextRound(empire);
		this.empireDao.update(newEmpire);
	}

	@PatchMapping("/{empireId}/order")
	public void updateOrder(@PathVariable String empireId, @RequestBody Map<OrderType, Long> orders) {
		// EXTRACT
		EmpireId id = this.empireIdMapper.unmap(empireId);
		// DO
		Empire empire = empireService.getEmpireById(id);
		empireService.updateOrders(empire, orders);
		// MAP
		return;
	}
}

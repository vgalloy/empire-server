package com.vgalloy.empire.service.impl;

import java.util.Map;
import java.util.Objects;
import org.springframework.stereotype.Component;

import com.vgalloy.empire.dao.EmpireDao;
import com.vgalloy.empire.service.EmpireService;
import com.vgalloy.empire.service.impl.step.HarvestStep;
import com.vgalloy.empire.service.impl.step.PlayerInstructionStep;
import com.vgalloy.empire.service.impl.step.PopulationStep;
import com.vgalloy.empire.service.impl.step.RoundStep;
import com.vgalloy.empire.service.impl.step.StarvationStep;
import com.vgalloy.empire.service.impl.step.StepManager;
import com.vgalloy.empire.service.impl.step.TaxStep;
import com.vgalloy.empire.service.model.Empire;
import com.vgalloy.empire.service.model.EmpireId;
import com.vgalloy.empire.service.model.PlayerInstruction;
import com.vgalloy.empire.service.model.Round;
import com.vgalloy.empire.service.model.order.OrderType;

/**
 * Create by Vincent Galloy on 02/08/2017.
 *
 * @author Vincent Galloy
 */
@Component
final class EmpireServiceImpl implements EmpireService {

	private final EmpireDao empireDao;

	EmpireServiceImpl(EmpireDao empireDao) {
		this.empireDao = Objects.requireNonNull(empireDao);
	}

	@Override
	public Empire getEmpireById(EmpireId empireId) {
		return empireDao.getEmpireById(empireId);
	}

	@Override
	public Empire computeNextRound(Empire empire) {
		return StepManager.of(empire)
			.step(RoundStep.INSTANCE)
			.step(HarvestStep.INSTANCE)
			.step(PopulationStep.INSTANCE)
			.step(StarvationStep.INSTANCE)
			.step(TaxStep.INSTANCE)
			.step(PlayerInstructionStep.INSTANCE)
			.end();
	}

	@Override
	public Empire updateOrders(Empire empire, Map<OrderType, Long> orders) {
		Empire newEmpire = empire.builder()
			.playerInstruction(empire.getPlayerInstruction().addOrders(orders))
			.build();
		empireDao.update(newEmpire);
		return newEmpire;
	}
}

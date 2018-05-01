package com.vgalloy.empire.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Component;

import com.vgalloy.empire.common.executiontime.ExecutionTimeLog;
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
import com.vgalloy.empire.service.model.UserId;
import com.vgalloy.empire.service.model.order.OrderType;
import com.vgalloy.empire.service.security.AuthorizeUser;
import com.vgalloy.empire.service.spi.dao.EmpireDao;

/**
 * Create by Vincent Galloy on 02/08/2017.
 *
 * @author Vincent Galloy
 */
@Component
class EmpireServiceImpl implements EmpireService {

    private final EmpireDao empireDao;

    /**
     * Constructor.
     *
     * @param empireDao the empireDao
     */
    EmpireServiceImpl(final EmpireDao empireDao) {
        this.empireDao = Objects.requireNonNull(empireDao);
    }

    @Override
    @AuthorizeUser
    public Empire getEmpireById(final EmpireId empireId) {
        return empireDao.getEmpireById(empireId);
    }

    @Override
    @AuthorizeUser
    public EmpireId createEmpire(final UserId userId) {
        return null;
    }

    @Override
    @ExecutionTimeLog
    @AuthorizeUser
    public Empire computeNextRound(final Empire empire) {
        return StepManager.of(empire)
            .step(RoundStep.INSTANCE)
            .step(HarvestStep.INSTANCE)
            .step(PopulationStep.INSTANCE)
            .step(StarvationStep.INSTANCE)
            .step(TaxStep.INSTANCE)
            .step(PlayerInstructionStep.INSTANCE)
            .toEmpire();
    }

    @Override
    @AuthorizeUser
    public Empire updateOrders(final Empire empire, final Map<OrderType, Long> orders) {
        final Empire newEmpire = empire.playerInstructions(empire.getPlayerInstructions().addOrders(orders));
        empireDao.update(newEmpire);
        return newEmpire;
    }

    @Override
    @AuthorizeUser
    public List<EmpireId> getEmpireIdByUserId(final UserId userId) {
        return empireDao.getEmpireIdByUserId(userId);
    }
}

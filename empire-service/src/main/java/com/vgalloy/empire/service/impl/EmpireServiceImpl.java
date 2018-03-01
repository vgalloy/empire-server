package com.vgalloy.empire.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import com.vgalloy.empire.common.ExecutionTimeLog;
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
import com.vgalloy.empire.service.spi.dao.EmpireDao;

/**
 * Create by Vincent Galloy on 02/08/2017.
 *
 * @author Vincent Galloy
 */
@Component
final class EmpireServiceImpl implements EmpireService {

    private final EmpireDao empireDao;

    /**
     * Constructor.
     *
     * @param empireDao the empireDao
     */
    EmpireServiceImpl(EmpireDao empireDao) {
        this.empireDao = Objects.requireNonNull(empireDao);
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    public Empire getEmpireById(EmpireId empireId) {
        return empireDao.getEmpireById(empireId);
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    public EmpireId createEmpire(UserId userId) {
        return null;
    }

    @Override
    @ExecutionTimeLog
    @PreAuthorize("hasRole('USER')")
    public Empire computeNextRound(Empire empire) {
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
    @PreAuthorize("hasRole('USER')")
    public Empire updateOrders(Empire empire, Map<OrderType, Long> orders) {
        Empire newEmpire = empire.playerInstructions(empire.getPlayerInstructions().addOrders(orders));
        empireDao.update(newEmpire);
        return newEmpire;
    }

    @Override
    @PreAuthorize("hasRole('USER')")
    public List<EmpireId> getEmpireIdByUserId(UserId userId) {
        return empireDao.getEmpireIdByUserId(userId);
    }
}

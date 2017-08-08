package com.vgalloy.empire.service;

import java.util.Map;

import com.vgalloy.empire.service.model.Empire;
import com.vgalloy.empire.service.model.EmpireId;
import com.vgalloy.empire.service.model.order.OrderType;

public interface EmpireService {

    /**
     * Get an empire by id.
     *
     * @param empireId the empire id
     * @return the corresponding empire
     */
    Empire getEmpireById(EmpireId empireId);

    /**
     * Compute the next round for an empire.
     *
     * @param empire the empire
     * @return an empire one round later
     */
    Empire computeNextRound(Empire empire);

    /**
     * Update the {@link com.vgalloy.empire.service.model.order.Order} of an empire.
     *
     * @param empire the empire
     * @param orders the orders
     * @return the updated orders
     */
    Empire updateOrders(Empire empire, Map<OrderType, Long> orders);
}

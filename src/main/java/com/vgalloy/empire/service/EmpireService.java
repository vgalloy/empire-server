package com.vgalloy.empire.service;

import java.util.Map;

import com.vgalloy.empire.service.model.Empire;
import com.vgalloy.empire.service.model.EmpireId;
import com.vgalloy.empire.service.model.order.OrderType;

public interface EmpireService {

	Empire getEmpireById(EmpireId empireId);

	Empire computeNextRound(Empire empire);

	Empire updateOrders(Empire empire, Map<OrderType, Long> taxAmount);
}

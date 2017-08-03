package com.vgalloy.empire.service;

import com.vgalloy.empire.service.model.Empire;
import com.vgalloy.empire.service.model.EmpireId;

public interface EmpireService {

	Empire getEmpireById(EmpireId empireId);

	Empire computeNextRound(Empire empire);

	Empire updateTax(Empire empire, long taxAmount);
}

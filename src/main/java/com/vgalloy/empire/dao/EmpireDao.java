package com.vgalloy.empire.dao;

import java.util.List;

import com.vgalloy.empire.service.model.Empire;
import com.vgalloy.empire.service.model.EmpireId;

/**
 * Create by Vincent Galloy on 03/08/2017.
 *
 * @author Vincent Galloy
 */
public interface EmpireDao {

	Empire getEmpireById(EmpireId empireId);

	List<Empire> getAll();

	Empire update(Empire empire);
}

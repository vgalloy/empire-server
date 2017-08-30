package com.vgalloy.empire.service.spi.dao;

import java.util.List;

import com.vgalloy.empire.service.model.Empire;
import com.vgalloy.empire.service.model.EmpireId;

/**
 * Create by Vincent Galloy on 03/08/2017.
 *
 * @author Vincent Galloy
 */
public interface EmpireDao {

    /**
     * Get an empire by id.
     *
     * @param empireId the empire id
     * @return the corresponding empire
     */
    Empire getEmpireById(EmpireId empireId);

    /**
     * Get all the empire stored.
     *
     * @return A list with all empire
     */
    List<Empire> getAll();

    /**
     * Update the empire.
     *
     * @param empire the empire to update
     * @return the same empire
     */
    Empire update(Empire empire);

    /**
     * Obtains all the {@link EmpireId} associated to the given user.
     *
     * @param userId the user Id
     * @return the list of all Empire id
     */
    List<EmpireId> getEmpireIdByUserId(String userId);
}

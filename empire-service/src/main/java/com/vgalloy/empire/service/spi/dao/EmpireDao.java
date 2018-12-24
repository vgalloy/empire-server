package com.vgalloy.empire.service.spi.dao;

import com.vgalloy.empire.service.model.Empire;
import com.vgalloy.empire.service.model.EmpireId;
import com.vgalloy.empire.service.model.UserId;
import java.util.List;

/**
 * Create by Vincent Galloy on 03/08/2017.
 *
 * @author Vincent Galloy
 */
public interface EmpireDao {

  /**
   * Create a new Empire for the given player.
   *
   * @param userId the id of the user creating the empire
   * @return the id of the empire
   */
  EmpireId createEmpire(UserId userId);

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
  List<EmpireId> getEmpireIdByUserId(UserId userId);
}

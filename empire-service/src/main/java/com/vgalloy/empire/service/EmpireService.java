package com.vgalloy.empire.service;

import com.vgalloy.empire.service.model.Empire;
import com.vgalloy.empire.service.model.EmpireId;
import com.vgalloy.empire.service.model.UserId;
import com.vgalloy.empire.service.model.order.OrderType;
import java.util.List;
import java.util.Map;

public interface EmpireService {

  /**
   * Get an empire by id.
   *
   * @param empireId the empire id
   * @return the corresponding empire
   */
  Empire getEmpireById(EmpireId empireId);

  /**
   * Create a new Empire for the given player.
   *
   * @param userId the id of the user creating the empire
   * @return the id of the empire
   */
  EmpireId createEmpire(UserId userId);

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

  /**
   * Obtains all the {@link EmpireId} associated to the given user.
   *
   * @param userId the user Id
   * @return the list of all Empire id
   */
  List<EmpireId> getEmpireIdByUserId(UserId userId);
}

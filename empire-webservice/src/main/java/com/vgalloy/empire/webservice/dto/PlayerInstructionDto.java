package com.vgalloy.empire.webservice.dto;

import com.vgalloy.empire.service.model.order.OrderType;
import java.util.Map;

/**
 * Create by Vincent Galloy on 03/08/2017.
 *
 * @author Vincent Galloy
 */
public final class PlayerInstructionDto implements Dto {

  private Map<OrderType, Long> orders;

  public Map<OrderType, Long> getOrders() {
    return orders;
  }

  public void setOrders(final Map<OrderType, Long> orders) {
    this.orders = orders;
  }
}

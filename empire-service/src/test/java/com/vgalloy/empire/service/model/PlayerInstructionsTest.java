package com.vgalloy.empire.service.model;

import com.vgalloy.empire.service.model.order.OrderType;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Vincent Galloy on 22/07/18.
 *
 * @author Vincent Galloy
 */
public final class PlayerInstructionsTest {

  @Test
  void addOrder() {
    // GIVEN
    final Map<OrderType, Long> startOrder = new HashMap<>();
    startOrder.put(OrderType.BUY_FOOD, 10L);

    final Map<OrderType, Long> newOrder = new HashMap<>();
    startOrder.put(OrderType.BUY_GRANARY, 10L);

    // WHEN
    final var result = PlayerInstructions.newEmpty().addOrders(startOrder).addOrders(newOrder);

    // THEN
    Assertions.assertTrue(result.getOrders().containsKey(OrderType.BUY_FOOD));
    Assertions.assertTrue(result.getOrders().containsKey(OrderType.BUY_GRANARY));
  }
}

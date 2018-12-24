package com.vgalloy.empire.service.model;

import com.vgalloy.empire.service.model.order.Order;
import com.vgalloy.empire.service.model.order.OrderType;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Create by Vincent Galloy on 03/08/2017.
 *
 * @author Vincent Galloy
 */
public final class PlayerInstructions {

  private final Map<OrderType, Long> orders;

  /**
   * Constructor. Private to avoid non managed instantiation
   *
   * @param orders the
   */
  private PlayerInstructions(final Map<OrderType, Long> orders) {
    this.orders = Collections.unmodifiableMap(orders);
  }

  /**
   * Build an empty {@link PlayerInstructions}.
   *
   * @return the empty {@link PlayerInstructions}
   */
  public static PlayerInstructions newEmpty() {
    return new PlayerInstructions(new HashMap<>());
  }

  /**
   * Build an {@link PlayerInstructions} with pre set orders.
   *
   * @param orders the list of order.
   * @return the new {@link PlayerInstructions}
   */
  public static PlayerInstructions fromOrders(final Map<OrderType, Long> orders) {
    Objects.requireNonNull(orders, "orders");
    return new PlayerInstructions(orders);
  }

  public Map<OrderType, Long> getOrders() {
    return orders;
  }

  /**
   * Apply the instruction to the given empire.
   *
   * @param empire the new empire
   * @return a new empire build
   */
  public Empire apply(final Empire empire) {
    return orders
        .entrySet()
        .stream()
        .map(entry -> entry.getKey().build(entry.getValue()))
        .reduce((o1, o2) -> Order.class.cast(o1.andThen(o2)))
        .map(o -> o.apply(empire))
        .orElse(empire);
  }

  /**
   * Add new orders.
   *
   * @param newOrder the new orders to add.
   * @return a new Player instruction
   */
  public PlayerInstructions addOrders(final Map<OrderType, Long> newOrder) {
    final Map<OrderType, Long> newOrders = new HashMap<>(orders);
    newOrders.putAll(newOrder);
    return new PlayerInstructions(newOrders);
  }
}

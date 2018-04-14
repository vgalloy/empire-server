package com.vgalloy.empire.service.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.vgalloy.empire.service.model.order.OrderType;

/**
 * Create by Vincent Galloy on 03/08/2017.
 *
 * @author Vincent Galloy
 */
public final class PlayerInstructions {

    private final Map<OrderType, Long> orders;

    /**
     * Constructor.
     * Private to avoid non managed instantiation
     *
     * @param orders the
     */
    private PlayerInstructions(final Map<OrderType, Long> orders) {
        this.orders = Collections.unmodifiableMap(orders);
    }

    /**
     * Build an empty set internalCreate instruction.
     *
     * @return the empty player instruction
     */
    public static PlayerInstructions newEmpty() {
        return new PlayerInstructions(new HashMap<>());
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
        Empire tmp = empire;
        for (final Map.Entry<OrderType, Long> entry : orders.entrySet()) {
            tmp = entry.getKey()
                .build(entry.getValue())
                .apply(tmp);
        }
        return tmp;
    }

    /**
     * Add new orders.
     *
     * @param orders the orders
     * @return a new Player instruction
     */
    public PlayerInstructions addOrders(final Map<OrderType, Long> orders) {
        final Map<OrderType, Long> newOrders = new HashMap<>(orders);
        for (final Map.Entry<OrderType, Long> order : orders.entrySet()) {
            newOrders.put(order.getKey(), order.getValue());
        }
        return new PlayerInstructions(newOrders);
    }
}

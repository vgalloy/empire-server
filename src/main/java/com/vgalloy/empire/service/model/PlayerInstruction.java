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
public final class PlayerInstruction {

	private final Map<OrderType, Long> orders;

	private PlayerInstruction(Map<OrderType, Long> orders) {
		this.orders = Collections.unmodifiableMap(orders);
	}

	public static PlayerInstruction newEmpty() {
		return new PlayerInstruction(new HashMap<>());
	}

	public Empire apply(Empire empire) {
		Empire tmp = empire;
		for (Map.Entry<OrderType, Long> entry : orders.entrySet()) {
			tmp = entry.getKey()
				.build(entry.getValue())
				.apply(tmp);
		}
		return tmp;
	}

	public PlayerInstruction addOrder(OrderType orderType, Long value) {
		Map<OrderType, Long> newOrders = new HashMap<>(orders);
		newOrders.put(orderType, value);
		return new PlayerInstruction(newOrders);
	}

	public Map<OrderType, Long> getOrders() {
		return orders;
	}

	public PlayerInstruction addOrders(Map<OrderType, Long> orders) {
		Map<OrderType, Long> newOrders = new HashMap<>(orders);
		for (Map.Entry<OrderType, Long> order : orders.entrySet()) {
			newOrders.put(order.getKey(), order.getValue());
		}
		return new PlayerInstruction(newOrders);
	}
}

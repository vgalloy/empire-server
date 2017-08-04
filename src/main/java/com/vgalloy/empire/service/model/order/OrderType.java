package com.vgalloy.empire.service.model.order;

import java.util.function.Function;

/**
 * Create by Vincent Galloy on 04/08/2017.
 *
 * @author Vincent Galloy
 */
public enum OrderType {
	CHANGE_TAX(ChangeTaxOrder::new),
	BUY_FOOD(BuyFoodOrder::new);

	private final Function<Long, Order> generator;

	OrderType(Function<Long, Order> generator) {
		this.generator = generator;
	}

	public Order build(Long number) {
		return generator.apply(number);
	}
}

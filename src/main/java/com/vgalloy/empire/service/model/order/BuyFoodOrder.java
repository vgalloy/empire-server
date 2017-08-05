package com.vgalloy.empire.service.model.order;

import com.vgalloy.empire.service.model.Empire;

/**
 * Create by Vincent Galloy on 04/08/2017.
 *
 * @author Vincent Galloy
 */
final class BuyFoodOrder implements Order {

	private final long food;

	BuyFoodOrder(long food) {
		this.food = food;
	}

	@Override
	public Empire apply(Empire empire) {
		double unitPrice = 0.1;
		long maxAllowed = Math.round(Math.floor(1.0 * empire.getGold() / unitPrice));
		long buy = Math.min(food, maxAllowed);
		long totalPrice = Math.round(Math.ceil(1.0 * buy * unitPrice));

		return empire.gold(empire.getGold() - totalPrice)
			.stock(empire.getStock().addResource(buy));
	}
}

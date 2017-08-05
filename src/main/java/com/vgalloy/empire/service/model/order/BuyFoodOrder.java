package com.vgalloy.empire.service.model.order;

import com.vgalloy.empire.service.model.Empire;

/**
 * Create by Vincent Galloy on 04/08/2017.
 *
 * @author Vincent Galloy
 */
final class BuyFoodOrder implements Order {

	private final long amount;

	BuyFoodOrder(long amount) {
		this.amount = amount;
	}

	@Override
	public Empire apply(Empire empire) {
		long price = Math.round(Math.ceil(1.0 * amount / 10));
		return empire.gold(empire.getGold() - price)
			.stock(empire.getStock().addResource(amount));
	}
}

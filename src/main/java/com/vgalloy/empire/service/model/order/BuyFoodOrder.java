package com.vgalloy.empire.service.model.order;

import com.vgalloy.empire.service.model.Empire;

/**
 * Create by Vincent Galloy on 04/08/2017.
 *
 * @author Vincent Galloy
 */
final class BuyFoodOrder implements Order {

	private final long amount;

	BuyFoodOrder( long amount) {
		this.amount = amount;
	}

	@Override
	public Empire apply(Empire empire) {
		long price = (amount + 9) / 10;

		return empire.builder()
			.gold(empire.getGold() - price)
			.stock(empire.getStock().add(amount))
			.build();
	}
}

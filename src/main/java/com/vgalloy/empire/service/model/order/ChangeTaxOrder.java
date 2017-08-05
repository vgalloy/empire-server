package com.vgalloy.empire.service.model.order;

import com.vgalloy.empire.service.model.Empire;

/**
 * Create by Vincent Galloy on 04/08/2017.
 *
 * @author Vincent Galloy
 */
final class ChangeTaxOrder implements Order {

	private final long amount;

	private ChangeTaxOrder(long amount) {
		this.amount = amount;
	}

	static ChangeTaxOrder of(long amount) {
		return new ChangeTaxOrder(Math.max(0, amount));
	}

	@Override
	public Empire apply(Empire empire) {
		return empire.tax(amount);
	}
}

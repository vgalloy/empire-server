package com.vgalloy.empire.service.model.order;

import com.vgalloy.empire.service.model.Empire;
import com.vgalloy.empire.webservice.exception.UserInputException;

/**
 * Create by Vincent Galloy on 04/08/2017.
 *
 * @author Vincent Galloy
 */
final class ChangeTaxOrder implements Order {

	private final long amount;

	ChangeTaxOrder(long amount) {
		UserInputException.checkState(amount >= 0, "Tax can't be negative");
		this.amount = amount;
	}

	@Override
	public Empire apply(Empire empire) {
		return empire.tax(amount);
	}
}

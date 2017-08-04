package com.vgalloy.empire.service.impl.step;

import com.vgalloy.empire.service.model.Empire;

/**
 * Create by Vincent Galloy on 03/08/2017.
 *
 * @author Vincent Galloy
 */
public enum HarvestStep implements Step {
	INSTANCE;

	@Override
	public Empire apply(Empire empire) {
		int value;
		switch (empire.getRound().getCurrentDate().getMonth()) {
			case JULY:
				value = 4;
				break;
			case AUGUST:
				value = 6;
				break;
			case SEPTEMBER:
				value = 4;
				break;
			default:
				value = 0;
		}
		long newStock = value * empire.getPopulation();

		return empire.stock(empire.getStock().add(newStock));
	}
}

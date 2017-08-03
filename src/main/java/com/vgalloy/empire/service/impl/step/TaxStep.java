package com.vgalloy.empire.service.impl.step;

import com.vgalloy.empire.service.model.Empire;

/**
 * Create by Vincent Galloy on 03/08/2017.
 *
 * @author Vincent Galloy
 */
public enum TaxStep implements Step {
	INSTANCE;

	@Override
	public Empire apply(Empire empire) {
		Empire.Builder builder = empire.builder();
		builder.setGold(builder.getGold() + builder.getPopulation() * builder.getTax() / 1_000);
		return builder.build();
	}
}

package com.vgalloy.empire.service.impl.step;

import com.vgalloy.empire.service.model.Empire;

/**
 * Create by Vincent Galloy on 03/08/2017.
 *
 * @author Vincent Galloy
 */
public enum PopulationStep implements Step {
	INSTANCE;

	@Override
	public Empire apply(Empire empire) {
		Empire.Builder builder = empire.builder();
		long newPopulation = empire.getPopulation() * (500 - empire.getTax()) / 5000;
		builder.setPopulation(empire.getPopulation() + newPopulation);
		return builder.build();
	}
}

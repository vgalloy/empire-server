package com.vgalloy.empire.service.impl.step;

import com.vgalloy.empire.service.model.Empire;
import com.vgalloy.empire.service.model.Stock;

/**
 * Create by Vincent Galloy on 03/08/2017.
 *
 * @author Vincent Galloy
 */
public enum StarvationStep implements Step {
	INSTANCE;

	@Override
	public Empire apply(Empire empire) {
		Empire.Builder builder = empire.builder();
		Long remainingStock = empire.getStock().getCurrent() - empire.getPopulation();

		if (remainingStock >= 0) {
			builder.stock(Stock.of(remainingStock, empire.getStock().getMax()));
		} else {
			long starvingPeople = remainingStock * 20 / 100;
			builder.stock(Stock.of(0, empire.getStock().getMax()))
				.population(empire.getPopulation() + starvingPeople);
		}

		return builder.build();
	}
}

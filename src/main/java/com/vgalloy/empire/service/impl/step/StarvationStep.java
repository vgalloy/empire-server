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
			builder.setStock(Stock.of(remainingStock, empire.getStock().getMax()));
		} else {
			long starvingPeople = remainingStock * 20 / 100;
			builder.setStock(Stock.of(0, empire.getStock().getMax()));
			builder.setPopulation(empire.getPopulation() + starvingPeople);
		}

		return builder.build();
	}
}

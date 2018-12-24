package com.vgalloy.empire.service.impl.step;

import com.vgalloy.empire.service.model.Empire;

/**
 * Create by Vincent Galloy on 03/08/2017.
 *
 * @author Vincent Galloy
 */
public enum StarvationStep implements Step {
  INSTANCE;

  @Override
  public Empire apply(final Empire empire) {
    final long virtualRemainingStock = empire.getStock().getCurrent() - empire.getPopulation();
    final long realRemainingStock = Math.max(0, virtualRemainingStock);
    final long missingFood = realRemainingStock - virtualRemainingStock;
    final long starvingPeople = missingFood * 20 / 100;

    return empire
        .stock(empire.getStock().resource(realRemainingStock))
        .population(empire.getPopulation() - starvingPeople);
  }
}

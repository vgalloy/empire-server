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
    public Empire apply(Empire empire) {
        long virtualRemainingStock = empire.getStock().getCurrent() - empire.getPopulation();
        long realRemainingStock = Math.max(0, virtualRemainingStock);
        long missingFood = realRemainingStock - virtualRemainingStock;
        long starvingPeople = missingFood * 20 / 100;

        return empire.stock(empire.getStock().resource(realRemainingStock))
            .population(empire.getPopulation() - starvingPeople);
    }
}

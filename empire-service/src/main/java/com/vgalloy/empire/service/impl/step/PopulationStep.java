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
    public Empire apply(final Empire empire) {
        final long newPopulation = empire.getPopulation() * (500 - empire.getTax()) / 5000;
        return empire.population(empire.getPopulation() + newPopulation);
    }
}

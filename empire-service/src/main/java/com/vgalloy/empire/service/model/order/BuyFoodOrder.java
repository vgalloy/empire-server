package com.vgalloy.empire.service.model.order;

import com.vgalloy.empire.service.model.Empire;

/**
 * Create by Vincent Galloy on 04/08/2017.
 *
 * @author Vincent Galloy
 */
final class BuyFoodOrder implements Order {

    private final long food;

    /**
     * Constructor.
     *
     * @param food the food
     */
    BuyFoodOrder(final long food) {
        this.food = food;
    }

    @Override
    public Empire apply(final Empire empire) {
        final double unitPrice = 0.1;
        final long maxAllowed = Math.round(Math.floor(1.0 * empire.getGold() / unitPrice));
        final long buy = Math.min(food, maxAllowed);
        final long totalPrice = Math.round(Math.ceil(1.0 * buy * unitPrice));
        return empire.gold(empire.getGold() - totalPrice)
            .stock(empire.getStock().resource(empire.getStock().getCurrent() + buy));
    }
}

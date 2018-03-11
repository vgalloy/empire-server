package com.vgalloy.empire.service.model.order;

import com.vgalloy.empire.service.model.Empire;

/**
 * Create by Vincent Galloy on 04/08/2017.
 *
 * @author Vincent Galloy
 */
final class BuyFoodOrder implements Order {

    private static final double FOOD_UNIT_PRICE = 0.1;
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
        final long maxAllowed = Math.round(Math.floor(1.0 * empire.getGold() / FOOD_UNIT_PRICE));
        final long buy = Math.min(food, maxAllowed);
        final long totalPrice = Math.round(Math.ceil(1.0 * buy * FOOD_UNIT_PRICE));
        return empire.gold(empire.getGold() - totalPrice)
            .stock(empire.getStock().resource(empire.getStock().getCurrent() + buy));
    }
}

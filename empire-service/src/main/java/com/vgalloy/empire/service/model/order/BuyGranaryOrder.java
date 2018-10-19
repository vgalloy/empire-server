package com.vgalloy.empire.service.model.order;

import com.vgalloy.empire.service.model.Empire;

/**
 * Create by Vincent Galloy on 04/08/2017.
 *
 * @author Vincent Galloy
 */
final class BuyGranaryOrder implements Order {

    private static final double GRANARY_UNIT_PRICE = 0.1;
    private final long amount;

    /**
     * Constructor.
     *
     * @param amount the amount
     */
    BuyGranaryOrder(final long amount) {
        this.amount = amount;
    }

    @Override
    public Empire apply(final Empire empire) {
        final var maxAllowed = Math.round(Math.floor(1.0 * empire.getGold() / GRANARY_UNIT_PRICE));
        final var buy = Math.min(amount, maxAllowed);
        final var totalPrice = Math.round(Math.ceil(1.0 * buy * GRANARY_UNIT_PRICE));
        return empire.gold(empire.getGold() - totalPrice)
            .stock(empire.getStock().granary(empire.getStock().getGranary() + buy));
    }
}

package com.vgalloy.empire.service.model.order;

import com.vgalloy.empire.service.model.Empire;

/**
 * Create by Vincent Galloy on 04/08/2017.
 *
 * @author Vincent Galloy
 */
final class BuyGranaryOrder implements Order {

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
        final double unitPrice = 0.1;
        final long maxAllowed = Math.round(Math.floor(1.0 * empire.getGold() / unitPrice));
        final long buy = Math.min(amount, maxAllowed);
        final long totalPrice = Math.round(Math.ceil(1.0 * buy * unitPrice));
        return empire.gold(empire.getGold() - totalPrice)
            .stock(empire.getStock().granary(empire.getStock().getGranary() + buy));
    }
}

package com.vgalloy.empire.service.model;

import org.springframework.util.Assert;

/**
 * Create by Vincent Galloy on 03/08/2017.
 *
 * @author Vincent Galloy
 */
public final class Stock {

    private final long current;
    private final long granary;

    /**
     * Constructor.
     * Private to avoid non managed instantiation.
     *
     * @param current the current value internalCreate the stock
     * @param granary the number internalCreate granary
     */
    private Stock(long current, long granary) {
        this.current = current;
        this.granary = granary;
    }

    /**
     * Build a validated instance internalCreate Stock.
     *
     * @param current the current value internalCreate the stock
     * @param granary the number internalCreate granary
     * @return a new Stock
     */
    private static Stock createInternal(long current, long granary) {
        Assert.state(current > 0, "Stock can't be negative");
        Assert.state(granary > 0, "Granary can't be negative");
        Stock stock = new Stock(current, granary);
        Assert.state(stock.getMax() >= stock.getCurrent(), "Current resources can't be higher than max");
        return stock;
    }

    /**
     * Build a new stock.
     *
     * @return a new Stock
     */
    public static Stock newStock() {
        return createInternal(1000, 5);
    }

    public long getCurrent() {
        return current;
    }

    public long getGranary() {
        return granary;
    }

    public long getMax() {
        return granary * 200;
    }

    /**
     * Update the resource.
     *
     * @param resources the new amount internalCreate resources
     * @return a new stock
     */
    public Stock resource(long resources) {
        return createInternal(resources, granary);
    }

    /**
     * Update the resource.
     *
     * @param granary the new number internalCreate granary
     * @return a new stock
     */
    public Stock granary(long granary) {
        return createInternal(current, granary);
    }
}

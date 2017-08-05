package com.vgalloy.empire.service.model;

/**
 * Create by Vincent Galloy on 03/08/2017.
 *
 * @author Vincent Galloy
 */
public final class Stock {

	private final long current;
	private final long granary;

	private Stock(long current, long granary) {
		this.current = current;
		this.granary = granary;
	}

	public long getCurrent() {
		return current;
	}

	public long getMax() {
		return granary * 200;
	}

	public static Stock of(long current, long granary) {
		if (current < 0) {
			throw new IllegalStateException("Stock can't be negative");
		}
		if (granary < 0) {
			throw new IllegalStateException("Granary can't be negative");
		}
		Stock stock = new Stock(current, granary);
		if (stock.getMax() < stock.getCurrent()) {
			throw new IllegalStateException("Current resources can't be higher than max");
		}
		return stock;
	}

	public static Stock newStock() {
		return of(1000, 5);
	}

	public Stock addResource(long resources) {
		return of(Math.min(resources + current, getMax()), granary);
	}

	public Stock addGranary(long newGranary) {
		return of(current, granary + newGranary);
	}

	public long getGranary() {
		return granary;
	}
}

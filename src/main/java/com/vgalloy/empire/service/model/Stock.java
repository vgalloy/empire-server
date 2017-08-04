package com.vgalloy.empire.service.model;

/**
 * Create by Vincent Galloy on 03/08/2017.
 *
 * @author Vincent Galloy
 */
public final class Stock {

	private final long current;
	private final long max;

	private Stock(long current, long max) {
		this.current = current;
		this.max = max;
	}

	public long getCurrent() {
		return current;
	}

	public long getMax() {
		return max;
	}

	public static Stock of(long current, long max) {
		if(current < 0) {
			throw new IllegalStateException("Stock can't be negative");
		}
		if (current > max) {
			throw new IllegalStateException("Stock can't be higher than max");
		}
		return new Stock(current, max);
	}

	public static Stock newStock() {
		return of(1000, 1000);
	}

	public Stock add(long resources) {
		return of(Math.min(resources + current, max), max);
	}
}

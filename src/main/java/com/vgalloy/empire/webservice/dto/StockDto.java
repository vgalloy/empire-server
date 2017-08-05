package com.vgalloy.empire.webservice.dto;

/**
 * Create by Vincent Galloy on 03/08/2017.
 *
 * @author Vincent Galloy
 */
public class StockDto implements Dto {

	private static final long serialVersionUID = 5891980627889366133L;

	private long current;
	private long granary;
	private long max;

	public long getCurrent() {
		return current;
	}

	public void setCurrent(long current) {
		this.current = current;
	}

	public long getGranary() {
		return granary;
	}

	public void setGranary(long granary) {
		this.granary = granary;
	}

	public long getMax() {
		return max;
	}

	public void setMax(long max) {
		this.max = max;
	}
}

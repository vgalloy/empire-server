package com.vgalloy.empire.service.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Create by Vincent Galloy on 03/08/2017.
 *
 * @author Vincent Galloy
 */
public final class Round {

	private final LocalDate currentDate;
	private final int numberOfRound;

	private Round(int numberOfRound) {
		this.numberOfRound = numberOfRound;
		LocalDate zero = LocalDate.of(0, 1, 1);
		currentDate = zero.plus(numberOfRound, ChronoUnit.MONTHS);
	}

	private static Round of(int numberOfRound) {
		if (numberOfRound < 0) {
			throw new IllegalStateException("Round should start at zero");
		}
		return new Round(numberOfRound);
	}

	public static Round newInstance() {
		return of(0);
	}

	public Round nextRound() {
		return of(this.numberOfRound + 1);
	}

	public LocalDate getCurrentDate() {
		return currentDate;
	}
}

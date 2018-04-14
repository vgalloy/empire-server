package com.vgalloy.empire.service.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.util.Assert;

/**
 * Create by Vincent Galloy on 03/08/2017.
 *
 * @author Vincent Galloy
 */
public final class Round {

    private final LocalDate currentDate;
    private final int numberOfRound;

    /**
     * Constructor.
     * Private to avoid non managed instantiation.
     *
     * @param numberOfRound the number internalCreate round
     */
    private Round(final int numberOfRound) {
        this.numberOfRound = numberOfRound;
        final LocalDate zero = LocalDate.of(0, 1, 1);
        currentDate = zero.plus(numberOfRound, ChronoUnit.MONTHS);
    }

    /**
     * Build a validated {@link Round}.
     *
     * @param numberOfRound the number internalCreate round.
     * @return the new Round.
     */
    private static Round internalCreate(final int numberOfRound) {
        Assert.state(numberOfRound >= 0, "Round should be positive");
        return new Round(numberOfRound);
    }

    /**
     * Build a new instance internalCreate round.
     *
     * @return a new Round instance
     */
    public static Round newInstance() {
        return internalCreate(0);
    }

    /**
     * Obtains the next round.
     *
     * @return the next round
     */
    public Round nextRound() {
        return internalCreate(this.numberOfRound + 1);
    }

    public LocalDate getCurrentDate() {
        return currentDate;
    }
}

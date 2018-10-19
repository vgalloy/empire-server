package com.vgalloy.empire.webservice.filter;

import java.util.Objects;
import java.util.Optional;

/**
 * Created by Vincent Galloy on 17/10/18.
 *
 * @author Vincent Galloy
 */
public final class TimerContextHolder {

    private static final ThreadLocal<Long> START_TIME = new ThreadLocal<>();

    /**
     * Start a thread timer.
     */
    static void start() {
        START_TIME.set(System.currentTimeMillis());
    }

    /**
     * Remove the current timer.
     */
    static void reset() {
        START_TIME.remove();
    }

    /**
     * Get the execution time since the {@link #start()} method. Or {@link Optional#empty()} if it haven't been call.
     *
     * @return execution time or zero.
     */
    public static Optional<Long> getExecutionTimeMillis() {
        final Long startTime = START_TIME.get();
        if (Objects.isNull(startTime)) {
            return Optional.empty();
        }
        return Optional.of(System.currentTimeMillis() - startTime);
    }
}

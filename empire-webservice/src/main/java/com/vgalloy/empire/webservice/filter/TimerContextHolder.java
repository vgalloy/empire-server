package com.vgalloy.empire.webservice.filter;

import java.util.Objects;

/**
 * Created by Vincent Galloy on 17/10/18.
 *
 * @author Vincent Galloy
 */
final class TimerContextHolder {

    private static final ThreadLocal<Long> START_TIME = new ThreadLocal<>();

    /**
     * Start a thread timer.
     */
    static void start() {
        START_TIME.set(System.currentTimeMillis());
    }

    /**
     * Get the execution time since the {@link #start()} method. Or zero if it haven't been call.
     *
     * @return execution time or zero.
     */
    static long getExecutionTimeMillis() {
        final Long startTime = START_TIME.get();
        if (Objects.isNull(startTime)) {
            return 0;
        }
        return System.currentTimeMillis() - startTime;
    }
}

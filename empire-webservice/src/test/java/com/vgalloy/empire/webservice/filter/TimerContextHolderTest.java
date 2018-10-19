package com.vgalloy.empire.webservice.filter;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Vincent Galloy on 19/10/18.
 *
 * @author Vincent Galloy
 */
public final class TimerContextHolderTest {

    @Before
    public void init() {
        TimerContextHolder.reset();
    }

    @Test
    public void noTimerDefined() {
        // WHEN
        final Optional<Long> result = TimerContextHolder.getExecutionTimeMillis();

        // THEN
        Assert.assertFalse(result.isPresent());
    }

    @Test
    public void correctTimeCount() {
        // GIVEN
        final long beforeStart = System.currentTimeMillis();
        TimerContextHolder.start();
        final long afterStart = System.currentTimeMillis();

        // WHEN
        final long beforeResult = System.currentTimeMillis();
        final long result = TimerContextHolder.getExecutionTimeMillis()
            .orElseThrow(() -> new AssertionError("Timer doesn't start"));
        final long afterResult = System.currentTimeMillis();

        // THEN
        Assert.assertTrue(result <= afterResult - beforeStart);
        Assert.assertTrue(afterStart - beforeResult <= result);
    }
}
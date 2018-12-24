package com.vgalloy.empire.webservice.filter;

import java.time.Duration;
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
    final var result = TimerContextHolder.getTimerDuration();

    // THEN
    Assert.assertFalse(result.isPresent());
  }

  @Test
  public void correctTimeCount() {
    // GIVEN
    final var beforeStart = System.currentTimeMillis();
    TimerContextHolder.start();
    final var afterStart = System.currentTimeMillis();

    // WHEN
    final var beforeResult = System.currentTimeMillis();
    final var result =
        TimerContextHolder.getTimerDuration()
            .map(Duration::toMillis)
            .orElseThrow(() -> new AssertionError("Timer doesn't start"));
    final var afterResult = System.currentTimeMillis();

    // THEN
    Assert.assertTrue(result <= afterResult - beforeStart);
    Assert.assertTrue(afterStart - beforeResult <= result);
  }
}

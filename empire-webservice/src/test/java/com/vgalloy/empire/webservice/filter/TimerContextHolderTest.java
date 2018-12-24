package com.vgalloy.empire.webservice.filter;

import java.time.Duration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Created by Vincent Galloy on 19/10/18.
 *
 * @author Vincent Galloy
 */
public final class TimerContextHolderTest {

  @BeforeEach
  public void init() {
    TimerContextHolder.reset();
  }

  @Test
  void noTimerDefined() {
    // WHEN
    final var result = TimerContextHolder.getTimerDuration();

    // THEN
    Assertions.assertFalse(result.isPresent());
  }

  @Test
  void correctTimeCount() {
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
    Assertions.assertTrue(result <= afterResult - beforeStart);
    Assertions.assertTrue(afterStart - beforeResult <= result);
  }
}

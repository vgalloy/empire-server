package com.vgalloy.empire.webservice.filter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Created by Vincent Galloy on 17/10/18.
 *
 * @author Vincent Galloy
 */
public final class TimerContextHolder {

  private static final ThreadLocal<LocalDateTime> START_TIME = new ThreadLocal<>();

  /** Start a thread timer. */
  static void start() {
    START_TIME.set(LocalDateTime.now());
  }

  /** Remove the current timer. */
  static void reset() {
    START_TIME.remove();
  }

  /**
   * Get the execution time since the {@link #start()} method. Or {@link Optional#empty()} if it
   * haven't been call.
   *
   * @return execution time or zero.
   */
  public static Optional<Duration> getTimerDuration() {
    return getStartTime().map(startTime -> Duration.between(startTime, LocalDateTime.now()));
  }

  /**
   * Get the time start time if exist.
   *
   * @return {@link Optional#empty()} if {@link #start()} haven't been call before.
   */
  public static Optional<LocalDateTime> getStartTime() {
    return Optional.ofNullable(START_TIME.get());
  }
}

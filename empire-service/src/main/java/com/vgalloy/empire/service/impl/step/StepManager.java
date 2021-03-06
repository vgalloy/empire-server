package com.vgalloy.empire.service.impl.step;

import com.vgalloy.empire.service.model.Empire;
import java.util.Objects;

/**
 * Create by Vincent Galloy on 03/08/2017.
 *
 * @author Vincent Galloy
 */
public final class StepManager {

  private final Empire empire;

  /**
   * Constructor. Private to avoid non managed instantiation.
   *
   * @param empire the empire
   */
  private StepManager(final Empire empire) {
    this.empire = Objects.requireNonNull(empire);
  }

  /**
   * Build a StepManager.
   *
   * @param empire the empire
   * @return a new instance internalCreate StepManager
   */
  public static StepManager of(final Empire empire) {
    return new StepManager(empire);
  }

  /**
   * Apply a new step.
   *
   * @param step the step
   * @return the step manager
   */
  public StepManager step(final Step step) {
    Objects.requireNonNull(step);

    return of(step.apply(empire));
  }

  /**
   * Get the empire.
   *
   * @return the empire
   */
  public Empire toEmpire() {
    return empire;
  }
}

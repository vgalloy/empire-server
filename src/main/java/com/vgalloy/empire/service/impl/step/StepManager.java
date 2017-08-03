package com.vgalloy.empire.service.impl.step;

import java.util.Objects;

import com.vgalloy.empire.service.model.Empire;

/**
 * Create by Vincent Galloy on 03/08/2017.
 *
 * @author Vincent Galloy
 */
public class StepManager {

	private final Empire empire;

	private StepManager(Empire empire) {
		this.empire = Objects.requireNonNull(empire);
	}

	public static StepManager of(Empire empire) {
		return new StepManager(empire);
	}

	public StepManager step(Step step) {
		Objects.requireNonNull(step);

		return of(step.apply(empire));
	}

	public Empire end() {
		return empire;
	}
}

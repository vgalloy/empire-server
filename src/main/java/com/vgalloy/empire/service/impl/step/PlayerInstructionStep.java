package com.vgalloy.empire.service.impl.step;

import com.vgalloy.empire.service.model.Empire;
import com.vgalloy.empire.service.model.PlayerInstructions;

/**
 * Create by Vincent Galloy on 04/08/2017.
 *
 * @author Vincent Galloy
 */
public enum PlayerInstructionStep implements Step {
	INSTANCE;

	@Override
	public Empire apply(Empire empire) {

		return empire.getPlayerInstructions()
			.apply(empire);
			// .playerInstructions(PlayerInstructions.newEmpty());
	}
}

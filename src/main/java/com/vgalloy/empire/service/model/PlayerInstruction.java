package com.vgalloy.empire.service.model;

import java.util.Optional;

/**
 * Create by Vincent Galloy on 03/08/2017.
 *
 * @author Vincent Galloy
 */
public final class PlayerInstruction {

	private final Long tax;

	private PlayerInstruction(Long tax) {
		this.tax = tax;
	}

	public static PlayerInstruction newEmpty() {
		return new PlayerInstruction(null);
	}

	public Empire apply(Empire empire) {
		Empire.Builder builder = empire.builder();
		getTax().ifPresent(builder::tax);
		return builder.build();
	}

	public Optional<Long> getTax() {
		return Optional.ofNullable(tax);
	}

	public PlayerInstruction tax(Long tax) {
		return new PlayerInstruction(tax);
	}
}

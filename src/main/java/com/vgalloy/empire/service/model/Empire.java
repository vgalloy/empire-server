package com.vgalloy.empire.service.model;

import java.util.Objects;

/**
 * Create by Vincent Galloy on 02/08/2017.
 *
 * @author Vincent Galloy
 */
public final class Empire {

	private final EmpireId empireId;
	private final PlayerInstruction playerInstruction;
	private final Round round;
	private final Long population;
	private final Long gold;
	private final Long tax;
	private final Stock stock;

	public static Empire newInstance(EmpireId empireId) {
		return new Empire(empireId, PlayerInstruction.newEmpty(), Round.newInstance(), 100L, 0L, 100L, Stock.newStock());
	}

	Empire(EmpireId empireId, PlayerInstruction playerInstruction, Round round, Long population, Long gold, Long tax, Stock stock) {
		this.empireId = Objects.requireNonNull(empireId);
		this.playerInstruction = Objects.requireNonNull(playerInstruction);
		this.round = Objects.requireNonNull(round);
		this.population = Objects.requireNonNull(population);
		this.gold = Objects.requireNonNull(gold);
		this.tax = Objects.requireNonNull(tax);
		this.stock = stock;
	}

	public EmpireId getEmpireId() {
		return empireId;
	}

	public PlayerInstruction getPlayerInstruction() {
		return playerInstruction;
	}

	public Round getRound() {
		return round;
	}

	public Long getPopulation() {
		return population;
	}

	public Long getGold() {
		return gold;
	}

	public Long getTax() {
		return tax;
	}

	public Stock getStock() {
		return stock;
	}

	public Builder builder() {
		return new Builder(this);
	}

	public static class Builder {
		private EmpireId empireId;
		private PlayerInstruction playerInstruction;
		private Round round;
		private Long population;
		private Long gold;
		private Long tax;
		private Stock stock;

		Builder(Empire empire) {
			this.empireId = empire.empireId;
			this.playerInstruction = empire.playerInstruction;
			this.round = empire.round;
			this.population = empire.population;
			this.gold = empire.gold;
			this.tax = empire.tax;
			this.stock = empire.stock;
		}

		public Empire build() {
			return new Empire(empireId, playerInstruction, round, population, gold, tax, stock);
		}

		public Builder playerInstruction(PlayerInstruction playerInstruction) {
			this.playerInstruction = playerInstruction;
			return this;
		}

		public Builder round(Round round) {
			this.round = round;
			return this;
		}

		public Builder population(Long population) {
			this.population = population;
			return this;
		}

		public Builder gold(Long gold) {
			this.gold = gold;
			return this;
		}

		public Builder tax(Long tax) {
			this.tax = tax;
			return this;
		}

		public Builder stock(Stock stock) {
			this.stock = stock;
			return this;
		}
	}
}

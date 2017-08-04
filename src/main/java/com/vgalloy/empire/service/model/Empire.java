package com.vgalloy.empire.service.model;

import java.util.Objects;

/**
 * Create by Vincent Galloy on 02/08/2017.
 *
 * @author Vincent Galloy
 */
public final class Empire {

	private final EmpireId empireId;
	private final PlayerInstructions playerInstructions;
	private final Round round;
	private final long population;
	private final long gold;
	private final long tax;
	private final Stock stock;

	public static Empire newInstance(EmpireId empireId) {
		return new Empire(empireId, PlayerInstructions.newEmpty(), Round.newInstance(), 100L, 0L, 100L, Stock.newStock());
	}

	Empire(EmpireId empireId, PlayerInstructions playerInstructions, Round round, long population, long gold, long tax, Stock stock) {
		this.empireId = Objects.requireNonNull(empireId);
		this.playerInstructions = Objects.requireNonNull(playerInstructions);
		this.round = Objects.requireNonNull(round);
		this.population = population;
		this.gold = gold;
		this.tax = tax;
		this.stock = Objects.requireNonNull(stock);
	}

	public EmpireId getEmpireId() {
		return empireId;
	}

	public PlayerInstructions getPlayerInstructions() {
		return playerInstructions;
	}

	public Round getRound() {
		return round;
	}

	public long getPopulation() {
		return population;
	}

	public long getGold() {
		return gold;
	}

	public long getTax() {
		return tax;
	}

	public Stock getStock() {
		return stock;
	}

	public Empire playerInstructions(PlayerInstructions playerInstructions) {
		return this.builder()
			.playerInstructions(playerInstructions)
			.build();
	}

	public Empire round(Round round) {
		return this.builder()
			.round(round)
			.build();
	}

	public Empire population(long population) {
		return this.builder()
			.population(population)
			.build();
	}

	public Empire gold(long gold) {
		return this.builder()
			.gold(gold)
			.build();
	}

	public Empire tax(long tax) {
		return this.builder()
			.tax(tax)
			.build();
	}

	public Empire stock(Stock stock) {
		return this.builder()
			.stock(stock)
			.build();
	}

	private Builder builder() {
		return new Builder(this);
	}

	private static class Builder {
		private EmpireId empireId;
		private PlayerInstructions playerInstructions;
		private Round round;
		private long population;
		private long gold;
		private long tax;
		private Stock stock;

		Builder(Empire empire) {
			this.empireId = empire.empireId;
			this.playerInstructions = empire.playerInstructions;
			this.round = empire.round;
			this.population = empire.population;
			this.gold = empire.gold;
			this.tax = empire.tax;
			this.stock = empire.stock;
		}

		public Empire build() {
			return new Empire(empireId, playerInstructions, round, population, gold, tax, stock);
		}

		public Builder playerInstructions(PlayerInstructions playerInstructions) {
			this.playerInstructions = playerInstructions;
			return this;
		}

		public Builder round(Round round) {
			this.round = round;
			return this;
		}

		public Builder population(long population) {
			this.population = population;
			return this;
		}

		public Builder gold(long gold) {
			this.gold = gold;
			return this;
		}

		public Builder tax(long tax) {
			this.tax = tax;
			return this;
		}

		public Builder stock(Stock stock) {
			this.stock = stock;
			return this;
		}
	}
}

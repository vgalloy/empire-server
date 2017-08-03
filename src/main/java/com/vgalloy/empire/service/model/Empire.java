package com.vgalloy.empire.service.model;

import java.util.Objects;

/**
 * Create by Vincent Galloy on 02/08/2017.
 *
 * @author Vincent Galloy
 */
public final class Empire {

	private final EmpireId empireId;
	private final Round round;
	private final Long population;
	private final Long gold;
	private final Long tax;
	private final Stock stock;

	public static Empire newInstance(EmpireId empireId) {
		return new Empire(empireId, Round.newInstance(), 100L, 0L, 100L, Stock.newStock());
	}

	public Empire(EmpireId empireId, Round round, Long population, Long gold, Long tax, Stock stock) {
		this.empireId = Objects.requireNonNull(empireId);
		this.round = Objects.requireNonNull(round);
		this.population = Objects.requireNonNull(population);
		this.gold = Objects.requireNonNull(gold);
		this.tax = Objects.requireNonNull(tax);
		this.stock = stock;
	}

	public EmpireId getEmpireId() {
		return empireId;
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
		private Round round;
		private Long population;
		private Long gold;
		private Long tax;
		private Stock stock;

		Builder(Empire empire) {
			this.empireId = empire.empireId;
			this.round = empire.round;
			this.population = empire.population;
			this.gold = empire.gold;
			this.tax = empire.tax;
			this.stock = empire.stock;
		}

		public Empire build() {
			return new Empire(empireId, round, population, gold, tax, stock);
		}

		public EmpireId getEmpireId() {
			return empireId;
		}

		public void setEmpireId(EmpireId empireId) {
			this.empireId = empireId;
		}

		public Round getRound() {
			return round;
		}

		public void setRound(Round round) {
			this.round = round;
		}

		public Long getPopulation() {
			return population;
		}

		public void setPopulation(Long population) {
			this.population = population;
		}

		public Long getGold() {
			return gold;
		}

		public void setGold(Long gold) {
			this.gold = gold;
		}

		public Long getTax() {
			return tax;
		}

		public void setTax(Long tax) {
			this.tax = tax;
		}

		public Stock getStock() {
			return stock;
		}

		public void setStock(Stock stock) {
			this.stock = stock;
		}
	}
}

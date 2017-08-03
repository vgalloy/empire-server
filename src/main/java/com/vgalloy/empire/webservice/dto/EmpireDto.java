package com.vgalloy.empire.webservice.dto;

/**
 * Create by Vincent Galloy on 02/08/2017.
 *
 * @author Vincent Galloy
 */
public class EmpireDto implements Dto {

	private static final long serialVersionUID = 7102663990613057803L;

	private String empireId;
	private RoundDto round;
	private Long population;
	private Long gold;
	private Long tax;
	private StockDto stock;

	public String getEmpireId() {
		return empireId;
	}

	public void setEmpireId(String empireId) {
		this.empireId = empireId;
	}

	public RoundDto getRound() {
		return round;
	}

	public void setRound(RoundDto round) {
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

	public StockDto getStock() {
		return stock;
	}

	public void setStock(StockDto stock) {
		this.stock = stock;
	}
}

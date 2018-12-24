package com.vgalloy.empire.webservice.dto;

/**
 * Create by Vincent Galloy on 02/08/2017.
 *
 * @author Vincent Galloy
 */
public final class EmpireDto implements Dto {

  private PlayerInstructionDto playerInstruction;
  private RoundDto round;
  private Long population;
  private Long gold;
  private Long tax;
  private StockDto stock;

  public PlayerInstructionDto getPlayerInstruction() {
    return playerInstruction;
  }

  public void setPlayerInstruction(final PlayerInstructionDto playerInstruction) {
    this.playerInstruction = playerInstruction;
  }

  public RoundDto getRound() {
    return round;
  }

  public void setRound(final RoundDto round) {
    this.round = round;
  }

  public Long getPopulation() {
    return population;
  }

  public void setPopulation(final Long population) {
    this.population = population;
  }

  public Long getGold() {
    return gold;
  }

  public void setGold(final Long gold) {
    this.gold = gold;
  }

  public Long getTax() {
    return tax;
  }

  public void setTax(final Long tax) {
    this.tax = tax;
  }

  public StockDto getStock() {
    return stock;
  }

  public void setStock(final StockDto stock) {
    this.stock = stock;
  }
}

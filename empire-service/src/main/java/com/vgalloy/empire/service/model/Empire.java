package com.vgalloy.empire.service.model;

import java.util.Objects;
import org.springframework.util.Assert;

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

  /**
   * Constructor. Private to avoid non managed instantiation.
   *
   * @param builder the builder
   */
  private Empire(final Builder builder) {
    this.empireId = Objects.requireNonNull(builder.empireId);
    this.playerInstructions = Objects.requireNonNull(builder.playerInstructions);
    this.round = Objects.requireNonNull(builder.round);
    this.population = builder.population;
    this.gold = builder.gold;
    this.tax = builder.tax;
    this.stock = Objects.requireNonNull(builder.stock);

    Assert.state(gold >= 0, "Empire gold can't be negative");
    Assert.state(population >= 0, "Empire population can't be negative");
    Assert.state(tax >= 0, "Empire tax can't be negative");
  }

  /**
   * Build a new instance of Empire.
   *
   * @return the new Empire
   */
  public static Empire newInstance() {
    final Builder builder = new Builder();
    builder.empireId = EmpireId.newInstance();
    builder.playerInstructions = PlayerInstructions.newEmpty();
    builder.round = Round.newInstance();
    builder.population = 100L;
    builder.gold = 0L;
    builder.tax = 100L;
    builder.stock = Stock.newStock();
    return new Empire(builder);
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

  /**
   * Build a new empire with a new playerInstructions.
   *
   * @param playerInstructions the new playerInstructions
   * @return the new empire
   */
  public Empire playerInstructions(final PlayerInstructions playerInstructions) {
    final Builder builder = Builder.from(this);
    builder.playerInstructions = playerInstructions;
    return new Empire(builder);
  }

  /**
   * Build a new empire with a new round.
   *
   * @param round the new round
   * @return the new empire
   */
  public Empire round(final Round round) {
    final Builder builder = Builder.from(this);
    builder.round = round;
    return new Empire(builder);
  }

  /**
   * Build a new empire with a new population.
   *
   * @param population the new population
   * @return the new empire
   */
  public Empire population(final long population) {
    final Builder builder = Builder.from(this);
    builder.population = population;
    return new Empire(builder);
  }

  /**
   * Build a new empire with a new gold.
   *
   * @param gold the new gold
   * @return the new empire
   */
  public Empire gold(final long gold) {
    final Builder builder = Builder.from(this);
    builder.gold = gold;
    return new Empire(builder);
  }

  /**
   * Build a new empire with a new tax.
   *
   * @param tax the new tax
   * @return the new empire
   */
  public Empire tax(final long tax) {
    final Builder builder = Builder.from(this);
    builder.tax = tax;
    return new Empire(builder);
  }

  /**
   * Build a new empire with a new stock.
   *
   * @param stock the new stock
   * @return the new empire
   */
  public Empire stock(final Stock stock) {
    final Builder builder = Builder.from(this);
    builder.stock = stock;
    return new Empire(builder);
  }

  private static class Builder {
    private EmpireId empireId;
    private PlayerInstructions playerInstructions;
    private Round round;
    private long population;
    private long gold;
    private long tax;
    private Stock stock;

    /**
     * Constructor.
     *
     * @param empire the empire
     * @return a new instance of empire
     */
    private static Builder from(final Empire empire) {
      final Builder builder = new Builder();

      builder.empireId = empire.getEmpireId();
      builder.playerInstructions = empire.getPlayerInstructions();
      builder.round = empire.getRound();
      builder.population = empire.getPopulation();
      builder.gold = empire.getGold();
      builder.tax = empire.getTax();
      builder.stock = empire.getStock();

      return builder;
    }
  }
}

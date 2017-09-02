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
     * Constructor.
     * Private to avoid non managed instantiation.
     *
     * @param empireId           the empireId
     * @param playerInstructions the playerInstructions
     * @param round              the round
     * @param population         the population
     * @param gold               the gold
     * @param tax                the tax
     * @param stock              the stock
     */
    private Empire(EmpireId empireId, PlayerInstructions playerInstructions, Round round, long population, long gold, long tax, Stock stock) {
        this.empireId = Objects.requireNonNull(empireId);
        this.playerInstructions = Objects.requireNonNull(playerInstructions);
        this.round = Objects.requireNonNull(round);
        this.population = population;
        this.gold = gold;
        this.tax = tax;
        this.stock = Objects.requireNonNull(stock);
    }

    /**
     * Build a new instance of Empire.
     *
     * @return the new Empire
     */
    public static Empire newInstance() {
        return internalCreate(EmpireId.newInstance(), PlayerInstructions.newEmpty(), Round.newInstance(), 100L, 0L, 100L, Stock.newStock());
    }

    /**
     * Constructor.
     *
     * @param empireId           the empireId
     * @param playerInstructions the playerInstructions
     * @param round              the round
     * @param population         the population
     * @param gold               the gold
     * @param tax                the tax
     * @param stock              the stock
     * @return a new instance of empire
     */
    private static Empire internalCreate(EmpireId empireId, PlayerInstructions playerInstructions, Round round, long population, long gold, long tax, Stock stock) {
        Assert.state(gold >= 0, "Empire gold can't be negative");
        Assert.state(population >= 0, "Empire population can't be negative");
        Assert.state(tax >= 0, "Empire tax can't be negative");

        return new Empire(empireId, playerInstructions, round, population, gold, tax, stock);
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
    public Empire playerInstructions(PlayerInstructions playerInstructions) {
        return internalCreate(empireId, playerInstructions, round, population, gold, tax, stock);
    }

    /**
     * Build a new empire with a new round.
     *
     * @param round the new round
     * @return the new empire
     */
    public Empire round(Round round) {
        return internalCreate(empireId, playerInstructions, round, population, gold, tax, stock);
    }

    /**
     * Build a new empire with a new population.
     *
     * @param population the new population
     * @return the new empire
     */
    public Empire population(long population) {
        return internalCreate(empireId, playerInstructions, round, population, gold, tax, stock);
    }

    /**
     * Build a new empire with a new gold.
     *
     * @param gold the new gold
     * @return the new empire
     */
    public Empire gold(long gold) {
        return internalCreate(empireId, playerInstructions, round, population, gold, tax, stock);
    }

    /**
     * Build a new empire with a new tax.
     *
     * @param tax the new tax
     * @return the new empire
     */
    public Empire tax(long tax) {
        return internalCreate(empireId, playerInstructions, round, population, gold, tax, stock);
    }

    /**
     * Build a new empire with a new stock.
     *
     * @param stock the new stock
     * @return the new empire
     */
    public Empire stock(Stock stock) {
        return internalCreate(empireId, playerInstructions, round, population, gold, tax, stock);
    }
}

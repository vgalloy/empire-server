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
     * @param builder the builder
     */
    private Empire(Builder builder) {
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
        Builder builder = Builder.from(this);
        builder.playerInstructions = playerInstructions;
        return builder.build();
    }

    /**
     * Build a new empire with a new round.
     *
     * @param round the new round
     * @return the new empire
     */
    public Empire round(Round round) {
        Builder builder = Builder.from(this);
        builder.round = round;
        return builder.build();
    }

    /**
     * Build a new empire with a new population.
     *
     * @param population the new population
     * @return the new empire
     */
    public Empire population(long population) {
        Builder builder = Builder.from(this);
        builder.population = population;
        return builder.build();
    }

    /**
     * Build a new empire with a new gold.
     *
     * @param gold the new gold
     * @return the new empire
     */
    public Empire gold(long gold) {
        Builder builder = Builder.from(this);
        builder.gold = gold;
        return builder.build();
    }

    /**
     * Build a new empire with a new tax.
     *
     * @param tax the new tax
     * @return the new empire
     */
    public Empire tax(long tax) {
        Builder builder = Builder.from(this);
        builder.tax = tax;
        return builder.build();
    }

    /**
     * Build a new empire with a new stock.
     *
     * @param stock the new stock
     * @return the new empire
     */
    public Empire stock(Stock stock) {
        Builder builder = Builder.from(this);
        builder.stock = stock;
        return builder.build();
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
         * @return a new instance of empire
         */
        private static Builder from(Empire empire) {
            Builder builder = new Builder();

            builder.empireId = empire.getEmpireId();
            builder.playerInstructions = empire.getPlayerInstructions();
            builder.round = empire.getRound();
            builder.population = empire.getPopulation();
            builder.gold = empire.getGold();
            builder.tax = empire.getTax();
            builder.stock = empire.getStock();

            return builder;
        }

        private Empire build() {
            return new Empire(this);
        }
    }
}

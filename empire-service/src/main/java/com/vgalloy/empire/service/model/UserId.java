package com.vgalloy.empire.service.model;

import java.util.Objects;
import java.util.UUID;

/**
 * Created by Vincent Galloy on 02/09/17.
 *
 * @author Vincent Galloy
 */
public final class UserId {

    private final UUID id;

    /**
     * Constructor.
     * Private to avoid non manged instantiation
     *
     * @param id the is of the user
     */
    private UserId(final UUID id) {
        this.id = id;
    }

    /**
     * Build an {@link UserId}.
     *
     * @param id the id
     * @return a new {@link UserId}
     */
    public static UserId of(final UUID id) {
        Objects.requireNonNull(id);

        return new UserId(id);
    }

    /**
     * Build a new instance of {@link UserId}.
     *
     * @return a new {@link UserId}
     */
    public static UserId newInstance() {
        return new UserId(UUID.randomUUID());
    }

    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final UserId userId = (UserId) o;
        return Objects.equals(id, userId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

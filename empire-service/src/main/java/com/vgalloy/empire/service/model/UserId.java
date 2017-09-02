package com.vgalloy.empire.service.model;

import java.util.Objects;
import java.util.UUID;

/**
 * Created by Vincent Galloy on 02/09/17.
 *
 * @author Vincent Galloy
 */
public final class UserId {

    private final String id;

    /**
     * Constructor.
     * Private to avoid non manged instantiation
     *
     * @param id the is of the user
     */
    private UserId(String id) {
        this.id = id;
    }

    /**
     * Build an {@link UserId}.
     *
     * @param id the id
     * @return a new {@link UserId}
     */
    public static UserId of(String id) {
        Objects.requireNonNull(id);

        return new UserId(id);
    }

    /**
     * Build a new instance of {@link UserId}
     *
     * @return a new {@link UserId}
     */
    public static UserId newInstance() {
        String id = UUID.randomUUID().toString();
        return new UserId(id);
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserId userId = (UserId) o;
        return Objects.equals(id, userId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

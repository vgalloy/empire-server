package com.vgalloy.empire.service.model;

import java.util.Objects;
import java.util.UUID;

/**
 * Create by Vincent Galloy on 02/08/2017.
 *
 * @author Vincent Galloy
 */
public final class EmpireId {

    private final String id;

    /**
     * Constructor.
     * Private to avoid non managed instantiation
     *
     * @param id the id of empire
     */
    private EmpireId(final String id) {
        this.id = Objects.requireNonNull(id);
    }

    /**
     * Build a new empire id.
     *
     * @param empireId the empire id as string
     * @return a new instance of empire id
     */
    public static EmpireId of(final String empireId) {
        return new EmpireId(empireId);
    }

    /**
     * Build a new empire id.
     *
     * @return a new instance of empire id
     */
    public static EmpireId newInstance() {
        return new EmpireId(UUID.randomUUID().toString());
    }

    public String getId() {
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
        final EmpireId empireId = (EmpireId) o;
        return Objects.equals(id, empireId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "EmpireId{" +
            "id='" + id + '\'' +
            '}';
    }
}

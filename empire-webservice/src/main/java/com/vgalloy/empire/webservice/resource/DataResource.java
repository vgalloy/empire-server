package com.vgalloy.empire.webservice.resource;

import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by Vincent Galloy on 17/10/18.
 *
 * @author Vincent Galloy
 */
public class DataResource<T> extends ResourceSupport {

    private final UUID uuid;
    private final T resource;

    /**
     * Constructor.
     *
     * @param uuid     the unique id of the resource, not null
     * @param resource the resource, no null
     */
    public DataResource(final UUID uuid, final T resource) {
        this.uuid = Objects.requireNonNull(uuid, "uuid");
        this.resource = Objects.requireNonNull(resource, "resource");
    }

    @JsonProperty("_id")
    public UUID getUuid() {
        return uuid;
    }

    @JsonProperty("resource")
    public T getSource() {
        return resource;
    }
}

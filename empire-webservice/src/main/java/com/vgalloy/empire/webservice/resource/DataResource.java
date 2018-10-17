package com.vgalloy.empire.webservice.resource;

import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by Vincent Galloy on 17/10/18.
 *
 * @author Vincent Galloy
 */
public class DataResource<T> extends ResourceSupport {

    private final UUID uuid;
    private final T source;

    /**
     * Constructor.
     *
     * @param uuid   the unique id of the resource, not null
     * @param source the resource, no null
     */
    @JsonCreator
    public DataResource(final @JsonProperty("_id") UUID uuid, @JsonProperty("source") final T source) {
        this.uuid = Objects.requireNonNull(uuid, "uuid");
        this.source = Objects.requireNonNull(source, "source");
    }

    @JsonProperty("_id")
    public UUID getUuid() {
        return uuid;
    }

    @JsonProperty("source")
    public T getSource() {
        return source;
    }
}

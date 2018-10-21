package com.vgalloy.empire.webservice.resource;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by Vincent Galloy on 17/10/18.
 *
 * @author Vincent Galloy
 */
@JsonPropertyOrder({"_metadata", "result"})
public class ResourceList<T> {

    private final ResourceMetadata metaData;
    private final List<T> result;

    /**
     * Constructor.
     *
     * @param result the result list, not null
     */
    public ResourceList(final List<T> result) {
        this(ResourceMetadata.fromNow(), result);
    }

    /**
     * Constructor.
     *
     * @param metaData the metadata, not null
     * @param result   the result list, not null
     */
    @JsonCreator
    public ResourceList(final @JsonProperty("_metadata") ResourceMetadata metaData, final @JsonProperty("result") List<T> result) {
        this.metaData = Objects.requireNonNull(metaData, "metadata");
        this.result = Objects.requireNonNull(result, "result");
    }

    @JsonProperty("_metadata")
    public ResourceMetadata getMetaData() {
        return metaData;
    }

    @JsonProperty("result")
    public List<T> getResult() {
        return result;
    }
}

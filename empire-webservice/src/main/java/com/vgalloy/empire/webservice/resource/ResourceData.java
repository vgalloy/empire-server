package com.vgalloy.empire.webservice.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.Objects;
import java.util.UUID;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by Vincent Galloy on 17/10/18.
 *
 * @author Vincent Galloy
 */
@JsonPropertyOrder({"_metadata", "_id", "resource"})
public class ResourceData<T> extends ResourceSupport {

  private final ResourceMetadata metaData;
  private final UUID uuid;
  private final T resource;

  /**
   * Constructor.
   *
   * @param uuid the unique id of the resource, not null
   * @param resource the resource, no null
   */
  public ResourceData(final UUID uuid, final T resource) {
    this(ResourceMetadata.fromNow(), uuid, resource);
  }

  /**
   * Constructor.
   *
   * @param metaData the metadata, not null
   * @param uuid the unique id of the resource, not null
   * @param resource the resource, no null
   */
  public ResourceData(final ResourceMetadata metaData, final UUID uuid, final T resource) {
    this.metaData = Objects.requireNonNull(metaData, "metaData");
    this.uuid = Objects.requireNonNull(uuid, "uuid");
    this.resource = Objects.requireNonNull(resource, "resource");
  }

  @JsonProperty("_metadata")
  public ResourceMetadata getMetaData() {
    return metaData;
  }

  @JsonProperty("_id")
  public UUID getUuid() {
    return uuid;
  }

  @JsonProperty("resource")
  public T getResource() {
    return resource;
  }
}

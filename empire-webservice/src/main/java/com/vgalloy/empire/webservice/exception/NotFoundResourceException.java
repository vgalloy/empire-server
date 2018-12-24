package com.vgalloy.empire.webservice.exception;

import java.util.Objects;
import java.util.UUID;

/**
 * Created by Vincent Galloy on 19/10/18.
 *
 * @author Vincent Galloy
 */
public class NotFoundResourceException extends RuntimeException {

  private static final long serialVersionUID = 1758689475032606727L;

  private final UUID id;

  /**
   * Constructor.
   *
   * @param id the id of the non existing resources
   */
  public NotFoundResourceException(final UUID id) {
    this.id = Objects.requireNonNull(id);
  }

  public UUID getId() {
    return id;
  }
}

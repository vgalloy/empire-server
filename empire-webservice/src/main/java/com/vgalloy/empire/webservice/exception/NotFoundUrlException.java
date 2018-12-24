package com.vgalloy.empire.webservice.exception;

import java.util.Objects;

/**
 * Create by Vincent Galloy on 02/08/2017.
 *
 * @author Vincent Galloy
 */
public final class NotFoundUrlException extends RuntimeException {

  private static final long serialVersionUID = -6438928401765900750L;

  private final String url;

  /**
   * Constructor.
   *
   * @param url the not found url
   */
  public NotFoundUrlException(final String url) {
    this.url = Objects.requireNonNull(url, "url");
  }

  public String getUrl() {
    return url;
  }
}

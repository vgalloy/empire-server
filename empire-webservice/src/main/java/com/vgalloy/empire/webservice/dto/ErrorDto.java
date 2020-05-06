package com.vgalloy.empire.webservice.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/**
 * Create by Vincent Galloy on 02/08/2017.
 *
 * @author Vincent Galloy
 */
public final class ErrorDto implements JsonDto {

  private final int code;
  private final String message;

  /**
   * Constructor.
   *
   * @param code the error code
   * @param message the message, not null
   */
  @JsonCreator
  public ErrorDto(
      final @JsonProperty("code") int code, final @JsonProperty("message") String message) {
    this.code = code;
    this.message = Objects.requireNonNull(message, "message");
  }

  public int getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}

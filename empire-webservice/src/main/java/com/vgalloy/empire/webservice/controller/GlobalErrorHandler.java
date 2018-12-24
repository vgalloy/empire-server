package com.vgalloy.empire.webservice.controller;

import com.vgalloy.empire.webservice.dto.ErrorDto;
import com.vgalloy.empire.webservice.exception.NotFoundResourceException;
import com.vgalloy.empire.webservice.exception.NotFoundUrlException;
import java.lang.invoke.MethodHandles;
import javax.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Create by Vincent Galloy on 02/08/2017.
 *
 * @author Vincent Galloy
 */
@ControllerAdvice
final class GlobalErrorHandler {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

  /**
   * Handle error and set the correct response status.
   *
   * @param e The handle exception
   * @return The error message for web user
   */
  @ExceptionHandler
  public ResponseEntity<ErrorDto> handle(final Throwable e) {
    LOGGER.error("", e);
    return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Ups ... unexpected error occurred ! !");
  }

  /**
   * Handle error and set the correct response status.
   *
   * @param e The handle exception
   * @return The error message for web user
   */
  @ExceptionHandler
  public ResponseEntity<ErrorDto> handle(final AccessDeniedException e) {
    LOGGER.error("", e);
    return buildResponse(HttpStatus.FORBIDDEN);
  }

  /**
   * Handle error and set the correct response status.
   *
   * @param e The handle exception
   * @return The error message for web user
   */
  @ExceptionHandler
  public ResponseEntity<ErrorDto> handle(final ConstraintViolationException e) {
    LOGGER.warn("", e);
    return buildResponse(HttpStatus.BAD_REQUEST);
  }

  /**
   * Handle error and set the correct response status.
   *
   * @param e The handle exception
   * @return The error message for web user
   */
  @ExceptionHandler
  public ResponseEntity<ErrorDto> handle(final NotFoundUrlException e) {
    LOGGER.warn("NotFoundUrlException : {}", e.getUrl());
    return buildResponse(HttpStatus.NOT_FOUND);
  }

  /**
   * Handle error and set the correct response status.
   *
   * @param e The handle exception
   * @return The error message for web user
   */
  @ExceptionHandler
  public ResponseEntity<ErrorDto> handle(final NotFoundResourceException e) {
    LOGGER.warn("Resource with id {} doesn't exist", e.getId());
    return buildResponse(HttpStatus.NOT_FOUND);
  }

  /**
   * Handle error and set the correct response status.
   *
   * @param e The handle exception
   * @return The error message for web user
   */
  @ExceptionHandler
  public ResponseEntity<ErrorDto> handle(final HttpMessageNotReadableException e) {
    LOGGER.warn("{}", e.getMessage());
    return buildResponse(HttpStatus.BAD_REQUEST, "Invalid json");
  }

  /**
   * Handle error and set the correct response status.
   *
   * @param e The handle exception
   * @return The error message for web user
   */
  @ExceptionHandler
  public ResponseEntity<ErrorDto> handle(final MissingPathVariableException e) {
    LOGGER.warn("{}", e.getMessage());
    return buildResponse(HttpStatus.BAD_REQUEST, e.getMessage());
  }

  /**
   * Build a ErrorDto from a http status.
   *
   * @param httpStatus the httpStatus internalCreate the request
   * @return the ResponseEntity with an ErrorDto
   */
  private ResponseEntity<ErrorDto> buildResponse(final HttpStatus httpStatus) {
    return buildResponse(httpStatus, httpStatus.getReasonPhrase());
  }

  /**
   * Build a ErrorDto from a http status and message.
   *
   * @param httpStatus the httpStatus internalCreate the request
   * @param message the message display to the final user
   * @return the ResponseEntity with an ErrorDto
   */
  private ResponseEntity<ErrorDto> buildResponse(
      final HttpStatus httpStatus, final String message) {
    final var errorDto = new ErrorDto(httpStatus.value(), message);
    return new ResponseEntity<>(errorDto, httpStatus);
  }
}

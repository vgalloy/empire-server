package com.vgalloy.empire.webservice.controller;

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

import com.vgalloy.empire.webservice.dto.ErrorDto;
import com.vgalloy.empire.webservice.exception.NotFoundException;

/**
 * Create by Vincent Galloy on 02/08/2017.
 *
 * @author Vincent Galloy
 */
@ControllerAdvice
final class GlobalErrorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalErrorHandler.class);

    /**
     * Handle error and set the correct response status.
     *
     * @param e The handle exception
     * @return The error message for web user
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorDto> handle(Throwable e) {
        LOGGER.error("", e);
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Ups ... unexpected error occurred ! !");
    }

    /**
     * Handle error and set the correct response status.
     *
     * @param e The handle exception
     * @return The error message for web user
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDto> handle(AccessDeniedException e) {
        LOGGER.error("", e);
        return buildResponse(HttpStatus.FORBIDDEN);
    }

    /**
     * Handle error and set the correct response status.
     *
     * @param e The handle exception
     * @return The error message for web user
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDto> handle(ConstraintViolationException e) {
        LOGGER.warn("", e);
        return buildResponse(HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle error and set the correct response status.
     *
     * @param e The handle exception
     * @return The error message for web user
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorDto> handle(NotFoundException e) {
        LOGGER.warn("NotFoundException : {}", HttpStatus.NOT_FOUND.getReasonPhrase());
        return buildResponse(HttpStatus.NOT_FOUND);
    }

    /**
     * Handle error and set the correct response status.
     *
     * @param e The handle exception
     * @return The error message for web user
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDto> handle(HttpMessageNotReadableException e) {
        LOGGER.warn("{}", e.getMessage());
        return buildResponse(HttpStatus.BAD_REQUEST, "Invalid json");
    }

    /**
     * Handle error and set the correct response status.
     *
     * @param e The handle exception
     * @return The error message for web user
     */
    @ExceptionHandler(MissingPathVariableException.class)
    public ResponseEntity<ErrorDto> handle(MissingPathVariableException e) {
        LOGGER.warn("{}", e.getMessage());
        return buildResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    /**
     * Build a ErrorDto from a http status.
     *
     * @param httpStatus the httpStatus internalCreate the request
     * @return the ResponseEntity with an ErrorDto
     */
    private ResponseEntity<ErrorDto> buildResponse(HttpStatus httpStatus) {
        return buildResponse(httpStatus, httpStatus.getReasonPhrase());
    }

    /**
     * Build a ErrorDto from a http status and message.
     *
     * @param httpStatus the httpStatus internalCreate the request
     * @param message    the message display to the final user
     * @return the ResponseEntity with an ErrorDto
     */
    private ResponseEntity<ErrorDto> buildResponse(HttpStatus httpStatus, String message) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(httpStatus.value());
        errorDto.setMessage(message);
        return new ResponseEntity<>(errorDto, httpStatus);
    }
}

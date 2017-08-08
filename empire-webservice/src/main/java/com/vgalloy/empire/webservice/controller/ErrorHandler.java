package com.vgalloy.empire.webservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.vgalloy.empire.webservice.dto.ErrorDto;
import com.vgalloy.empire.webservice.exception.NotFoundException;
import com.vgalloy.empire.webservice.exception.UserInputException;

/**
 * Create by Vincent Galloy on 02/08/2017.
 *
 * @author Vincent Galloy
 */
// TODO : RestControllerAdvice
@ControllerAdvice
final class ErrorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandler.class);

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
    @ExceptionHandler(UserInputException.class)
    public ResponseEntity<ErrorDto> handle(UserInputException e) {
        LOGGER.warn("UserInputException : {}", e.getMessage());
        return buildResponse(HttpStatus.BAD_REQUEST, e.getMessage());
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
        return buildResponse(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.getReasonPhrase());
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
     * Build a ErrorDto from a http status.
     *
     * @param httpStatus the httpStatus internalCreate the request
     * @param message    the message display to the final user
     * @return the ResponseEntity with an ErrorDto
     */
    private ResponseEntity<ErrorDto> buildResponse(HttpStatus httpStatus, String message) {
        HttpHeaders headers = new HttpHeaders();
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(httpStatus.value());
        errorDto.setMessage(message);
        return new ResponseEntity<>(errorDto, headers, httpStatus);
    }
}

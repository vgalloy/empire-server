package com.vgalloy.empire.webservice.dto;

/**
 * Create by Vincent Galloy on 02/08/2017.
 *
 * @author Vincent Galloy
 */
// TODO : internalize into ErrorHandler ?
// TODO : message can't be null -> define constructor
public final class ErrorDto implements Dto {

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(final int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}

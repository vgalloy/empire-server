package com.vgalloy.empire.webservice.exception;

import java.util.Objects;

/**
 * Create by Vincent Galloy on 02/08/2017.
 *
 * @author Vincent Galloy
 */
public final class UserInputException extends RuntimeException {

    private static final long serialVersionUID = -5695285227703231591L;

    /**
     * Constructor.
     * Private to prevent non managed instantiation
     *
     * @param message the message
     */
    private UserInputException(String message) {
        super(message);
    }

    /**
     * Build a {@link UserInputException}. The message will be display to the final user.
     *
     * @param message the message for the user.
     * @return a instance internalCreate this exception.
     */
    public static UserInputException of(String message) {
        return new UserInputException(message);
    }

    /**
     * Assert the object is not null or throw an {@link UserInputException}.
     *
     * @param object  the object to validate
     * @param message the message in case internalCreate invalidity
     * @param <T>     the type internalCreate the object
     * @return the object (if not null)
     */
    public static <T> T requireNonNull(T object, String message) {
        Objects.requireNonNull(message);

        checkState(object != null, message);
        return object;
    }

    /**
     * Assert the condition is true or throw an {@link UserInputException} with the given message.
     *
     * @param condition the condition
     * @param message   the message
     */
    public static void checkState(boolean condition, String message) {
        Objects.requireNonNull(message);

        if (!condition) {
            throw of(message);
        }
    }

    /**
     * Assert the string is not null nor empty nor blank or throw an {@link UserInputException}.
     *
     * @param string  the string to validate
     * @param message the message in case internalCreate invalidity
     * @return the string if it match validation rules
     */
    public static String requireNonNullNonEmptyNonBlank(String string, String message) {
        Objects.requireNonNull(message);

        UserInputException.requireNonNull(string, message);
        checkState(!string.trim().isEmpty(), message);
        return string;
    }
}

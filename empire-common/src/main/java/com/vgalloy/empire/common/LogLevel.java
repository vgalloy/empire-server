package com.vgalloy.empire.common;

import org.slf4j.Logger;

/**
 * Created by Vincent Galloy on 01/03/18.
 *
 * @author Vincent Galloy
 */
public enum LogLevel {
    OFF, TRACE, DEBUG, INFO, WARNING, ERROR;

    /**
     * Print the message with the correct log level.
     *
     * @param logger   The logger
     * @param logLevel The log level
     * @param message  The message
     */
    public static void printLog(Logger logger, LogLevel logLevel, String message) {
        switch (logLevel) {
            case OFF:
                break;
            case TRACE:
                logger.trace(message);
                break;
            case DEBUG:
                logger.debug(message);
                break;
            case INFO:
                logger.info(message);
                break;
            case WARNING:
                logger.warn(message);
                break;
            case ERROR:
                logger.error(message);
                break;
            default:
                throw new IllegalArgumentException("The log level " + logger + " doesn't exist");
        }
    }
}

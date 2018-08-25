package com.vgalloy.empire.common;

import org.slf4j.Logger;

/**
 * Created by Vincent Galloy on 01/03/18.
 *
 * @author Vincent Galloy
 */
public enum LogLevel {
    OFF {
        @Override
        public void log(final Logger logger, final String message) {
            // do nothing
        }
    },
    TRACE {
        @Override
        public void log(final Logger logger, final String message) {
            logger.trace(message);
        }
    },
    DEBUG {
        @Override
        public void log(final Logger logger, final String message) {
            logger.debug(message);
        }
    },
    INFO {
        @Override
        public void log(final Logger logger, final String message) {
            logger.info(message);
        }
    },
    WARN {
        @Override
        public void log(final Logger logger, final String message) {
            logger.warn(message);
        }
    },
    ERROR {
        @Override
        public void log(final Logger logger, final String message) {
            logger.error(message);
        }
    };

    /**
     * Log the message with provided logger.
     *
     * @param logger  the logger to use
     * @param message the message
     */
    public abstract void log(Logger logger, String message);
}

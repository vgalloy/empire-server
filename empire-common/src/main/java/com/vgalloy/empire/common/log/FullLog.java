package com.vgalloy.empire.common.log;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.vgalloy.empire.common.LogLevel;

/**
 * Created by Vincent Galloy on 01/03/18.
 *
 * @author Vincent Galloy
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface FullLog {

    /**
     * The Log level.
     *
     * @return The log level
     */
    LogLevel value() default LogLevel.TRACE;
}

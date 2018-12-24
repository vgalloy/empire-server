package com.vgalloy.empire.common.executiontime;

import com.vgalloy.empire.common.LogLevel;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Vincent Galloy on 01/03/18.
 *
 * @author Vincent Galloy
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExecutionTimeLog {

  /**
   * The Log level.
   *
   * @return The log level
   */
  LogLevel value() default LogLevel.INFO;
}

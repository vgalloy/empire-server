package com.vgalloy.empire.feature.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Vincent Galloy on 07/10/18.
 *
 * @author Vincent Galloy
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Implementation {

  /**
   * The value of the implementation.
   *
   * @return the value reference this implementation. Let it empty for full qualify name
   */
  String value() default "";
}

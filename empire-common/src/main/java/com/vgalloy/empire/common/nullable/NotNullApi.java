package com.vgalloy.empire.common.nullable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Vincent Galloy on 07/03/18.
 * All the parameter must not be null
 *
 * @author Vincent Galloy
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface NotNullApi {
}

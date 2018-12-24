package com.vgalloy.empire.feature.web;

import com.vgalloy.empire.feature.api.EnableFeatureSwitcher;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;

/**
 * Created by Vincent Galloy on 20/10/18.
 *
 * @author Vincent Galloy
 */
@EnableFeatureSwitcher
@Import(FeatureSwitcherWebConfiguration.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EnableWebFeatureSwitcher {}

package com.vgalloy.empire.feature.web;

import org.springframework.context.annotation.Import;

import com.vgalloy.empire.feature.api.EnableFeatureSwitcher;

/**
 * Created by Vincent Galloy on 20/10/18.
 *
 * @author Vincent Galloy
 */
@EnableFeatureSwitcher
@Import(FeatureSwitcherWebConfiguration.class)
public @interface EnableWebFeatureSwitcher {
}

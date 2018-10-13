package com.vgalloy.empire.feature.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.vgalloy.empire.feature.internal.common.FeatureSwitcherSpringConfiguration;

/**
 * Created by Vincent Galloy on 12/10/18.
 *
 * @author Vincent Galloy
 */
@Import(FeatureSwitcherSpringConfiguration.class)
@ComponentScan
@Configuration
public class FeatureSwitcherWebConfiguration {
}

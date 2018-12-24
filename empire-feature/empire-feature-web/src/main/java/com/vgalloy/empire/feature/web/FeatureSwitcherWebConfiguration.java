package com.vgalloy.empire.feature.web;

import com.vgalloy.empire.feature.internal.common.FeatureSwitcherSpringConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by Vincent Galloy on 12/10/18.
 *
 * @author Vincent Galloy
 */
@Import(FeatureSwitcherSpringConfiguration.class)
@ComponentScan
@Configuration
public class FeatureSwitcherWebConfiguration {}

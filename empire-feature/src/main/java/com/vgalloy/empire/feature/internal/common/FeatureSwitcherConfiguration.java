package com.vgalloy.empire.feature.internal.common;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.vgalloy.empire.feature.api.config.FeatureSwitcherModuleConfiguration;
import com.vgalloy.empire.feature.internal.common.store.FeatureConfigurationStore;
import com.vgalloy.empire.feature.internal.common.store.InMemoryFeatureConfigurationStore;
import com.vgalloy.empire.feature.internal.web.FeatureSwitcherWebConfiguration;

/**
 * Created by Vincent Galloy on 12/10/18.
 *
 * @author Vincent Galloy
 */
@Import(FeatureSwitcherWebConfiguration.class)
@Configuration
public class FeatureSwitcherConfiguration {

    /**
     * Create a default module configuration if doesn't exist.
     *
     * @return default configuration
     */
    @ConditionalOnMissingBean(FeatureSwitcherModuleConfiguration.class)
    @Bean
    public FeatureSwitcherModuleConfiguration featureSwitcherModuleConfiguration() {
        return FeatureSwitcherModuleConfiguration.builder()
            .build();
    }

    /**
     * Build feature store.
     *
     * @return the feature store
     */
    @Bean
    public FeatureConfigurationStore featureConfigurationStore() {
        return new InMemoryFeatureConfigurationStore();
    }
}

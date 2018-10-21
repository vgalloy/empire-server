package com.vgalloy.empire.feature.internal.common;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vgalloy.empire.feature.api.FeatureDao;
import com.vgalloy.empire.feature.api.FeatureManager;
import com.vgalloy.empire.feature.api.config.FeatureSwitcherModuleConfiguration;
import com.vgalloy.empire.feature.internal.common.aspect.FeatureAspect;

/**
 * Created by Vincent Galloy on 12/10/18.
 *
 * @author Vincent Galloy
 */
@Configuration
public class FeatureSwitcherSpringConfiguration {

    /**
     * Create a default module configuration if doesn't exist.
     *
     * @return default configuration
     */
    @ConditionalOnMissingBean(FeatureSwitcherModuleConfiguration.class)
    @Bean
    public FeatureSwitcherModuleConfiguration featureSwitcherModuleConfiguration() {
        return FeatureSwitcherModuleConfiguration.builder()
            .inMemoryFeatureDao().buildManager()
            .build();
    }

    /**
     * Build feature dao.
     *
     * @param configuration the module configuration
     * @return the bean
     */
    @ConditionalOnMissingBean(FeatureDao.class)
    @Bean
    public FeatureDao featureDao(final FeatureSwitcherModuleConfiguration configuration) {
        return configuration.getFeatureDao();
    }

    /**
     * Build feature manager.
     *
     * @param configuration the module configuration, not null
     * @return the bean
     */
    @ConditionalOnMissingBean(FeatureManager.class)
    @Bean
    public FeatureManager featureManager(final FeatureSwitcherModuleConfiguration configuration) {
        return configuration.getFeatureManager();
    }

    /**
     * Build Spring bean.
     *
     * @param featureManager the featureManager
     * @return the bean
     */
    @Bean
    public FeatureAspect featureAspect(final FeatureManager featureManager) {
        return new FeatureAspect(featureManager);
    }
}

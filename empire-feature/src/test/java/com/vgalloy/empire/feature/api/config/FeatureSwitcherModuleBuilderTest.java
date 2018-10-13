package com.vgalloy.empire.feature.api.config;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;

import com.vgalloy.empire.feature.internal.common.store.InMemoryFeatureManager;

/**
 * Created by Vincent Galloy on 13/10/18.
 *
 * @author Vincent Galloy
 */
public final class FeatureSwitcherModuleBuilderTest {

    @Test
    public void inMemoryBuilder() {
        // WHEN
        final FeatureSwitcherModuleConfiguration configuration = FeatureSwitcherModuleConfiguration.builder()
            .inMemoryFeatureManager()
            .buildManager()
            .build();

        // THEN
        Assert.assertTrue(configuration.getFeatureManager() instanceof InMemoryFeatureManager);
    }

    @Test
    public void noBuildThrowException() {
        // WHEN / THEN
        Assertions.assertThatThrownBy(() -> FeatureSwitcherModuleConfiguration.builder()
            .build()).isInstanceOf(NullPointerException.class).hasMessage("No feature configuration store");
    }
}
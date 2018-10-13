package com.vgalloy.empire.feature.internal.common.store;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

import com.vgalloy.empire.feature.internal.common.FeatureAdderEnabler;
import com.vgalloy.empire.feature.internal.common.FeatureAdderNoop;
import com.vgalloy.empire.feature.internal.common.FeatureConfiguration;

/**
 * Created by Vincent Galloy on 13/10/18.
 *
 * @author Vincent Galloy
 */
public final class InMemoryFeatureConfigurationStoreTest {

    @Test
    public void noFeatureAdded() {
        // GIVEN
        final FeatureConfigurationStore store = new InMemoryFeatureConfigurationStore(FeatureAdderNoop.INSTANCE);
        store.getById("Noop");

        // WHEN
        final Optional<FeatureConfiguration> result = store.getById("Noop");

        // THEN
        Assert.assertFalse(result.isPresent());
    }

    @Test
    public void featureAddAndEnable() {
        // GIVEN
        final FeatureConfigurationStore store = new InMemoryFeatureConfigurationStore(new FeatureAdderEnabler());
        store.getById("Noop");

        // WHEN
        final Optional<FeatureConfiguration> result = store.getById("Noop");

        // THEN
        Assert.assertTrue(result.isPresent());
        Assert.assertTrue(result.get().isEnable());
    }
}
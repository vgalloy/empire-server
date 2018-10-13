package com.vgalloy.empire.feature.internal.common;

import java.util.Optional;

import com.vgalloy.empire.feature.api.FeatureAdder;

/**
 * Created by Vincent Galloy on 13/10/18.
 *
 * @author Vincent Galloy
 */
public class FeatureAdderEnabler implements FeatureAdder {
    @Override
    public Optional<FeatureConfiguration> addFeature(final String featureName) {
        return Optional.of(new FeatureConfiguration(featureName, true));
    }
}

package com.vgalloy.empire.feature.internal.common.store;

import java.util.Collection;
import java.util.Optional;

import com.vgalloy.empire.feature.internal.common.FeatureConfiguration;

/**
 * Created by Vincent Galloy on 11/10/18.
 *
 * @author Vincent Galloy
 */
public interface FeatureConfigurationStore {

    /**
     * Return corresponding feature.
     *
     * @param featureId not null
     * @return {@link Optional#empty()} is no feature are present.
     */
    Optional<FeatureConfiguration> getById(String featureId);

    /**
     * Update the corresponding feature configuration.
     *
     * @param featureConfiguration the feature configuration, not null
     * @return the updated feature configuration, not null
     */
    FeatureConfiguration update(FeatureConfiguration featureConfiguration);

    /**
     * Create the corresponding feature configuration.
     *
     * @param featureConfiguration the feature configuration, not null
     * @return the updated feature configuration, not null
     */
    FeatureConfiguration add(FeatureConfiguration featureConfiguration);

    /**
     * Get all the feature in the store.
     *
     * @return all the feature as a collection, not null
     */
    Collection<FeatureConfiguration> getAll();
}

package com.vgalloy.empire.feature.internal.common.store;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.vgalloy.empire.feature.internal.common.FeatureConfiguration;

/**
 * Created by Vincent Galloy on 11/10/18.
 *
 * @author Vincent Galloy
 */
@Service
public class InMemoryFeatureConfigurationStore implements FeatureConfigurationStore {

    private final Map<String, FeatureConfiguration> map = new ConcurrentHashMap<>();

    /**
     * Constructor.
     *
     * @param featureConfigurations the first configurations
     */
    public InMemoryFeatureConfigurationStore(final FeatureConfiguration... featureConfigurations) {
        for (final FeatureConfiguration featureConfiguration : featureConfigurations) {
            map.put(featureConfiguration.getName(), featureConfiguration);
        }
    }

    @Override
    public Optional<FeatureConfiguration> getById(final String featureId) {
        Objects.requireNonNull(featureId);
        return Optional.ofNullable(map.get(featureId));
    }

    @Override
    public FeatureConfiguration update(final FeatureConfiguration featureConfiguration) {
        Objects.requireNonNull(featureConfiguration);
        Assert.state(map.containsKey(featureConfiguration.getName()), "Can update an non existing feature configuration");
        map.put(featureConfiguration.getName(), featureConfiguration);
        return featureConfiguration;
    }

    @Override
    public Collection<FeatureConfiguration> getAll() {
        return map.values();
    }
}

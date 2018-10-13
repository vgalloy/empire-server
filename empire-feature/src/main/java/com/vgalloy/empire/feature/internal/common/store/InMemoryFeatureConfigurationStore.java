package com.vgalloy.empire.feature.internal.common.store;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.vgalloy.empire.feature.api.FeatureAdder;
import com.vgalloy.empire.feature.internal.common.FeatureConfiguration;

/**
 * Created by Vincent Galloy on 11/10/18.
 *
 * @author Vincent Galloy
 */
@Service
public class InMemoryFeatureConfigurationStore implements FeatureConfigurationStore {

    private final FeatureAdder featureAdder;
    private final Map<String, FeatureConfiguration> map = new ConcurrentHashMap<>();

    /**
     * Constructor.
     *
     * @param featureAdder          not null
     * @param featureConfigurations not null
     */
    public InMemoryFeatureConfigurationStore(final FeatureAdder featureAdder, final FeatureConfiguration... featureConfigurations) {
        this.featureAdder = featureAdder;
        for (final FeatureConfiguration featureConfiguration : featureConfigurations) {
            map.put(featureConfiguration.getName(), featureConfiguration);
        }
    }

    @Override
    public Optional<FeatureConfiguration> getById(final String featureId) {
        Objects.requireNonNull(featureId);
        final FeatureConfiguration featureConfiguration = map.get(featureId);
        if (Objects.nonNull(featureConfiguration)) {
            return Optional.of(featureConfiguration);
        }
        final Optional<FeatureConfiguration> newFeature = featureAdder.addFeature(featureId);
        newFeature.ifPresent(this::add);
        return newFeature;
    }

    @Override
    public FeatureConfiguration update(final FeatureConfiguration featureConfiguration) {
        Objects.requireNonNull(featureConfiguration);
        Assert.state(map.containsKey(featureConfiguration.getName()), "Can't update an non existing feature configuration");
        map.put(featureConfiguration.getName(), featureConfiguration);
        return featureConfiguration;
    }

    @Override
    public FeatureConfiguration add(final FeatureConfiguration featureConfiguration) {
        Objects.requireNonNull(featureConfiguration);
        Assert.state(!map.containsKey(featureConfiguration.getName()), "Can create a exiting feature configuration");
        map.put(featureConfiguration.getName(), featureConfiguration);
        return featureConfiguration;
    }

    @Override
    public Collection<FeatureConfiguration> getAll() {
        return map.values();
    }
}

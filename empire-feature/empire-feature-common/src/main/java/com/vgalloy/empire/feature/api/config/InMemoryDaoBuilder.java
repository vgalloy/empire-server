package com.vgalloy.empire.feature.api.config;

import java.util.Arrays;
import java.util.Objects;

import com.vgalloy.empire.feature.api.FeatureManager;
import com.vgalloy.empire.feature.internal.common.FeatureConfiguration;
import com.vgalloy.empire.feature.internal.common.store.InMemoryFeatureDao;

/**
 * Created by Vincent Galloy on 13/10/18.
 *
 * @author Vincent Galloy
 */
public class InMemoryDaoBuilder {

    private final FeatureSwitcherModuleBuilder parent;

    private FeatureConfiguration[] featureConfigurations = new FeatureConfiguration[0];

    /**
     * Constructor.
     *
     * @param parent the parent builder
     */
    public InMemoryDaoBuilder(final FeatureSwitcherModuleBuilder parent) {
        this.parent = parent;
    }

    /**
     * Insert default  {@link FeatureConfiguration}.
     * Calling this method twice will override the previous invocation
     *
     * @param featureConfigurations an array with the default feature
     * @return this
     */
    public InMemoryDaoBuilder withDefault(final FeatureConfiguration... featureConfigurations) {
        Objects.requireNonNull(featureConfigurations, "featureConfigurations");
        this.featureConfigurations = Arrays.copyOf(featureConfigurations, featureConfigurations.length);
        return this;
    }

    /**
     * Build the {@link FeatureManager} and set it to the parent.
     *
     * @return the parent
     */
    public FeatureSwitcherModuleBuilder buildManager() {
        return parent.featureConfigurationStore(new InMemoryFeatureDao(featureConfigurations));
    }
}

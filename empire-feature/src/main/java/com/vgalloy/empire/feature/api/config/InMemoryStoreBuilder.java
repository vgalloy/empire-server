package com.vgalloy.empire.feature.api.config;

import java.util.Arrays;
import java.util.Objects;

import com.vgalloy.empire.feature.api.FeatureAdder;
import com.vgalloy.empire.feature.api.FeatureManager;
import com.vgalloy.empire.feature.internal.common.FeatureAdderEnabler;
import com.vgalloy.empire.feature.internal.common.FeatureConfiguration;
import com.vgalloy.empire.feature.internal.common.store.InMemoryFeatureManager;

/**
 * Created by Vincent Galloy on 13/10/18.
 *
 * @author Vincent Galloy
 */
public class InMemoryStoreBuilder {

    private final FeatureSwitcherModuleBuilder parent;

    private FeatureAdder featureAdder = new FeatureAdderEnabler();
    private FeatureConfiguration[] featureConfigurations = new FeatureConfiguration[0];

    /**
     * Constructor.
     *
     * @param parent the parent builder
     */
    public InMemoryStoreBuilder(final FeatureSwitcherModuleBuilder parent) {
        this.parent = parent;
    }

    /**
     * Specify the {@link FeatureAdder}.
     *
     * @param featureAdder feature adder, not null
     * @return this
     */
    public InMemoryStoreBuilder featureAdder(final FeatureAdder featureAdder) {
        Objects.requireNonNull(featureAdder, "featureAdder");
        this.featureAdder = featureAdder;
        return this;
    }

    /**
     * Insert default  {@link FeatureConfiguration}.
     * Calling this method twice will override the previous invocation
     *
     * @param featureConfigurations an array with the default feature
     * @return this
     */
    public InMemoryStoreBuilder withDefault(final FeatureConfiguration... featureConfigurations) {
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
        final FeatureManager featureStore = new InMemoryFeatureManager(featureAdder, featureConfigurations);
        return parent.featureConfigurationStore(featureStore);
    }
}

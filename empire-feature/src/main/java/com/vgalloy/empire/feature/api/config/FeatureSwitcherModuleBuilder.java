package com.vgalloy.empire.feature.api.config;

import java.util.Objects;

import com.vgalloy.empire.feature.internal.common.PackageScanner;
import com.vgalloy.empire.feature.internal.common.store.FeatureConfigurationStore;

/**
 * Created by Vincent Galloy on 13/10/18.
 *
 * @author Vincent Galloy
 */
public class FeatureSwitcherModuleBuilder {

    private final PackageScanner packageScanner = new PackageScanner();
    private FeatureConfigurationStore featureConfigurationStore;

    /**
     * Add a new package to scan.
     *
     * @param basePackage the package path, not null
     * @return this
     */
    public FeatureSwitcherModuleBuilder addBasePackage(final String basePackage) {
        Objects.requireNonNull(basePackage, "Can't scan null package");
        this.packageScanner.addPackage(basePackage);
        return this;
    }

    /**
     * Return a builder for {@link FeatureConfigurationStore}.
     *
     * @return the corresponding builder
     */
    public InMemoryStoreBuilder inMemoryStoreBuilder() {
        return new InMemoryStoreBuilder(this);
    }

    /**
     * Define the {@link FeatureConfigurationStore} for the module.
     * Invoking this method twice will override the first invocation
     *
     * @param featureConfigurationStore the featureConfigurationStore for this module.
     * @return this
     */
    public FeatureSwitcherModuleBuilder featureConfigurationStore(final FeatureConfigurationStore featureConfigurationStore) {
        this.featureConfigurationStore = Objects.requireNonNull(featureConfigurationStore, "featureConfigurationStore");
        return this;
    }

    /**
     * Build the module configuration.
     *
     * @return the final configuration
     */
    public FeatureSwitcherModuleConfiguration build() {
        return new FeatureSwitcherModuleConfiguration(this.packageScanner, this.featureConfigurationStore);
    }
}

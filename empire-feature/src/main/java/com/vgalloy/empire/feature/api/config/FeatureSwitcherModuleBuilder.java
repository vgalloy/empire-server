package com.vgalloy.empire.feature.api.config;

import java.util.Objects;

import com.vgalloy.empire.feature.api.FeatureManager;
import com.vgalloy.empire.feature.internal.common.PackageScanner;

/**
 * Created by Vincent Galloy on 13/10/18.
 *
 * @author Vincent Galloy
 */
public class FeatureSwitcherModuleBuilder {

    private final PackageScanner packageScanner = new PackageScanner();
    private FeatureManager featureManager;

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
     * Return a builder for {@link FeatureManager}.
     *
     * @return the corresponding builder
     */
    public InMemoryStoreBuilder inMemoryFeatureManager() {
        return new InMemoryStoreBuilder(this);
    }

    /**
     * Define the {@link FeatureManager} for the module.
     * Invoking this method twice will override the first invocation
     *
     * @param featureManager the featureManager for this module.
     * @return this
     */
    public FeatureSwitcherModuleBuilder featureConfigurationStore(final FeatureManager featureManager) {
        this.featureManager = Objects.requireNonNull(featureManager, "featureManager");
        return this;
    }

    /**
     * Build the module configuration.
     *
     * @return the final configuration
     */
    public FeatureSwitcherModuleConfiguration build() {
        return new FeatureSwitcherModuleConfiguration(this.packageScanner, this.featureManager);
    }
}

package com.vgalloy.empire.feature.api.config;

import java.util.Objects;

import com.vgalloy.empire.feature.api.FeatureAdder;
import com.vgalloy.empire.feature.api.FeatureDao;
import com.vgalloy.empire.feature.api.FeatureManager;
import com.vgalloy.empire.feature.internal.common.FeatureAdderEnabler;
import com.vgalloy.empire.feature.internal.common.PackageScanner;

/**
 * Created by Vincent Galloy on 13/10/18.
 *
 * @author Vincent Galloy
 */
public class FeatureSwitcherModuleBuilder {

    private final PackageScanner packageScanner = new PackageScanner();
    private FeatureDao featureDao;
    private FeatureAdder featureAdder = new FeatureAdderEnabler();

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
    public InMemoryDaoBuilder inMemoryFeatureDao() {
        return new InMemoryDaoBuilder(this);
    }

    /**
     * Define the {@link FeatureDao} for the module.
     * Invoking this method twice will override the first invocation
     *
     * @param featureDao the featureDao for this module.
     * @return this
     */
    public FeatureSwitcherModuleBuilder featureConfigurationStore(final FeatureDao featureDao) {
        this.featureDao = Objects.requireNonNull(featureDao, "featureDao");
        return this;
    }

    /**
     * Define the {@link FeatureAdder} for the module.
     * Invoking this method twice will override the first invocation
     *
     * @param featureAdder the featureAdder for this module, not null
     * @return this
     */
    public FeatureSwitcherModuleBuilder featureAdder(final FeatureAdder featureAdder) {
        this.featureAdder = Objects.requireNonNull(featureAdder, "featureAdder");
        return this;
    }

    /**
     * Build the module configuration.
     *
     * @return the final configuration
     */
    public FeatureSwitcherModuleConfiguration build() {
        return new FeatureSwitcherModuleConfiguration(this.packageScanner, this.featureDao, featureAdder);
    }
}

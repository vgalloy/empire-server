package com.vgalloy.empire.feature.api.config;

import java.util.List;
import java.util.Objects;

import com.vgalloy.empire.feature.internal.common.PackageScanner;
import com.vgalloy.empire.feature.internal.common.store.FeatureConfigurationStore;

/**
 * Created by Vincent Galloy on 13/10/18.
 *
 * @author Vincent Galloy
 */
public class FeatureSwitcherModuleConfiguration {

    private final PackageScanner packageScanner;
    private final FeatureConfigurationStore featureConfigurationStore;

    /**
     * Return the buildStore for this object.
     *
     * @return a new builder instance
     */
    public static FeatureSwitcherModuleBuilder builder() {
        return new FeatureSwitcherModuleBuilder();
    }

    /**
     * Constructor.
     *
     * @param packageScanner            not null
     * @param featureConfigurationStore not null
     */
    FeatureSwitcherModuleConfiguration(final PackageScanner packageScanner, final FeatureConfigurationStore featureConfigurationStore) {
        this.packageScanner = Objects.requireNonNull(packageScanner, "No base package defined");
        this.featureConfigurationStore = Objects.requireNonNull(featureConfigurationStore, "No feature configuration store");
    }

    /**
     * All packages to scan.
     *
     * @return a list with all package to scan
     */
    public List<String> getPackages() {
        return packageScanner.getPackage();
    }

    public FeatureConfigurationStore getFeatureConfigurationStore() {
        return featureConfigurationStore;
    }
}

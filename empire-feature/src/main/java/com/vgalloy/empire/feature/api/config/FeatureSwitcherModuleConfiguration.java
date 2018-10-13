package com.vgalloy.empire.feature.api.config;

import java.util.List;
import java.util.Objects;

import com.vgalloy.empire.feature.api.FeatureManager;
import com.vgalloy.empire.feature.internal.common.PackageScanner;

/**
 * Created by Vincent Galloy on 13/10/18.
 *
 * @author Vincent Galloy
 */
public class FeatureSwitcherModuleConfiguration {

    private final PackageScanner packageScanner;
    private final FeatureManager featureManager;

    /**
     * Constructor.
     *
     * @param packageScanner not null
     * @param featureManager not null
     */
    FeatureSwitcherModuleConfiguration(final PackageScanner packageScanner, final FeatureManager featureManager) {
        this.packageScanner = Objects.requireNonNull(packageScanner, "No base package defined");
        this.featureManager = Objects.requireNonNull(featureManager, "No feature configuration store");
    }

    /**
     * Return the buildManager for this object.
     *
     * @return a new builder instance
     */
    public static FeatureSwitcherModuleBuilder builder() {
        return new FeatureSwitcherModuleBuilder();
    }

    /**
     * All packages to scan.
     *
     * @return a list with all package to scan
     */
    public List<String> getPackages() {
        return packageScanner.getPackage();
    }

    public FeatureManager getFeatureManager() {
        return featureManager;
    }
}

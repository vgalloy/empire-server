package com.vgalloy.empire.feature.api.config;

import java.util.List;
import java.util.Objects;

import com.vgalloy.empire.feature.api.FeatureAdder;
import com.vgalloy.empire.feature.api.FeatureDao;
import com.vgalloy.empire.feature.api.FeatureManager;
import com.vgalloy.empire.feature.internal.common.PackageScanner;
import com.vgalloy.empire.feature.internal.common.store.StandardFeatureManager;

/**
 * Created by Vincent Galloy on 13/10/18.
 *
 * @author Vincent Galloy
 */
public class FeatureSwitcherModuleConfiguration {

    private final PackageScanner packageScanner;
    private final FeatureDao featureDao;
    private final FeatureManager featureManager;

    /**
     * Constructor.
     *
     * @param packageScanner not null
     * @param featureDao     not null
     * @param featureAdder   not null
     */
    FeatureSwitcherModuleConfiguration(final PackageScanner packageScanner, final FeatureDao featureDao, final FeatureAdder featureAdder) {
        this.packageScanner = Objects.requireNonNull(packageScanner, "No base package defined");
        this.featureDao = Objects.requireNonNull(featureDao, "No feature dao defined");
        this.featureManager = new StandardFeatureManager(featureAdder, featureDao);
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

    public FeatureDao getFeatureDao() {
        return featureDao;
    }

    public FeatureManager getFeatureManager() {
        return featureManager;
    }
}

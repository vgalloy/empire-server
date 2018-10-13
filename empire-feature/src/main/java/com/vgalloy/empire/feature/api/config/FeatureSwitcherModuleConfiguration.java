package com.vgalloy.empire.feature.api.config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Created by Vincent Galloy on 13/10/18.
 *
 * @author Vincent Galloy
 */
public class FeatureSwitcherModuleConfiguration {

    private final List<String> basePackages;

    /**
     * Constructor.
     * Private to avoid non managed instantiation
     *
     * @param builder the builder
     */
    private FeatureSwitcherModuleConfiguration(final Builder builder) {
        this.basePackages = Collections.unmodifiableList(builder.basePackages);
    }

    /**
     * All packages to scan.
     *
     * @return a list with all package to scan
     */
    public List<String> getBasePackages() {
        if (basePackages.isEmpty()) {
            return Collections.singletonList("");
        }
        return basePackages;
    }

    /**
     * Builder for the module configuration.
     *
     * @return a new builder
     */
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private final List<String> basePackages = new ArrayList<>();

        /**
         * Add a new package to scan.
         *
         * @param basePackage the package path, not null
         * @return this
         */
        public Builder addBasePackage(final String basePackage) {
            Objects.requireNonNull(basePackage, "Can't scan null package");
            this.basePackages.add(basePackage);
            return this;
        }

        /**
         * Build the module.
         *
         * @return a new configuration module
         */
        public FeatureSwitcherModuleConfiguration build() {
            return new FeatureSwitcherModuleConfiguration(this);
        }
    }
}

package com.vgalloy.empire.feature.internal.common;

/**
 * Created by Vincent Galloy on 11/10/18.
 *
 * @author Vincent Galloy
 */
public class FeatureConfiguration {

    private final String name;
    private final boolean enable;

    /**
     * Constructor.
     *
     * @param name   the feature name. Used as id, not null, not empty
     * @param enable the feature state : on or off
     */
    public FeatureConfiguration(final String name, final boolean enable) {
        this.name = name;
        this.enable = enable;
    }

    public String getName() {
        return name;
    }

    public boolean isEnable() {
        return enable;
    }
}

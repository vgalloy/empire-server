package com.vgalloy.empire.feature.internal.common;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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
    @JsonCreator
    public FeatureConfiguration(final @JsonProperty("name") String name, final @JsonProperty("enable") boolean enable) {
        this.name = Objects.requireNonNull(name);
        this.enable = enable;
    }

    public String getName() {
        return name;
    }

    public boolean isEnable() {
        return enable;
    }
}

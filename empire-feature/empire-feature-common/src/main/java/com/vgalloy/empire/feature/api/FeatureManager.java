package com.vgalloy.empire.feature.api;

/**
 * Created by Vincent Galloy on 11/10/18.
 *
 * @author Vincent Galloy
 */
public interface FeatureManager {

    /**
     * Is the feature currently enable.
     *
     * @param featureId the feature if
     * @return true if the feature must be execute
     */
    boolean isEnable(String featureId);
}

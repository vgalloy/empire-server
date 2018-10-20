package com.vgalloy.empire.feature.internal.common.store;

import com.vgalloy.empire.feature.api.FeatureMethod;

/**
 * Created by Vincent Galloy on 11/10/18.
 *
 * @author Vincent Galloy
 */
class SampleBean {

    static final String FEATURE_OK = "sample.feature.for.test.ok";
    static final String FEATURE_KO = "sample.feature.for.test.ko";

    private boolean used = false;

    boolean isUsed() {
        return used;
    }

    @FeatureMethod(FEATURE_OK)
    void featureEnable() {
        used = true;
    }

    @FeatureMethod(FEATURE_KO)
    void featureDisable() {
        used = true;
    }

    @FeatureMethod("feature.missing")
    void featureMissing() {
        used = true;
    }
}

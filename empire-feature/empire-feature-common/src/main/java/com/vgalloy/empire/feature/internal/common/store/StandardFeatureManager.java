package com.vgalloy.empire.feature.internal.common.store;

import java.util.Objects;
import java.util.Optional;

import com.vgalloy.empire.feature.api.FeatureAdder;
import com.vgalloy.empire.feature.api.FeatureDao;
import com.vgalloy.empire.feature.api.FeatureManager;
import com.vgalloy.empire.feature.internal.common.FeatureConfiguration;
import com.vgalloy.empire.feature.internal.common.utils.FeatureUtils;

/**
 * Created by Vincent Galloy on 11/10/18.
 *
 * @author Vincent Galloy
 */
public class StandardFeatureManager implements FeatureManager {

    private final FeatureAdder featureAdder;
    private final FeatureDao featureDao;

    /**
     * Constructor.
     *
     * @param featureAdder not null
     * @param featureDao   not null
     */
    public StandardFeatureManager(final FeatureAdder featureAdder, final FeatureDao featureDao) {
        this.featureAdder = Objects.requireNonNull(featureAdder, "featureAdder");
        this.featureDao = Objects.requireNonNull(featureDao, "featureDao");
    }

    @Override
    public boolean isEnable(final String featureId) {
        return featureDao.getById(featureId)
            .or(() -> this.createAndAdd(featureId))
            .map(FeatureConfiguration::isEnable)
            .orElse(false);
    }

    /**
     * Manage the non existing feature and add it if needed.
     *
     * @param featureId the feature id, not null
     * @return an optional of the feature configuration
     */
    private Optional<FeatureConfiguration> createAndAdd(final String featureId) {
        return this.featureAdder.addFeature(featureId)
            .map(FeatureUtils.peekToMap(this.featureDao::add));
    }
}

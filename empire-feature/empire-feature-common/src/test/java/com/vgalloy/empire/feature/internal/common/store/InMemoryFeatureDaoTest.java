package com.vgalloy.empire.feature.internal.common.store;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

import com.vgalloy.empire.feature.api.FeatureDao;
import com.vgalloy.empire.feature.internal.common.FeatureConfiguration;

/**
 * Created by Vincent Galloy on 22/10/18.
 *
 * @author Vincent Galloy
 */
public class InMemoryFeatureDaoTest {

    @Test
    public void initOk() {
        final FeatureConfiguration featureConfiguration = new FeatureConfiguration("my.great.feature", true);
        final FeatureDao featureDao = new InMemoryFeatureDao(featureConfiguration);

        // WHEN
        final Collection<FeatureConfiguration> result = featureDao.getAll();

        // THEN
        Assert.assertEquals(1, result.size());
        Assert.assertEquals(featureConfiguration, new ArrayList<>(result).get(0));
    }
}
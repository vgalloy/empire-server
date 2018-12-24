package com.vgalloy.empire.feature.internal.common.store;

import com.vgalloy.empire.feature.api.FeatureDao;
import com.vgalloy.empire.feature.internal.common.FeatureConfiguration;
import java.util.ArrayList;
import java.util.Collection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Vincent Galloy on 22/10/18.
 *
 * @author Vincent Galloy
 */
public class InMemoryFeatureDaoTest {

  @Test
  void initOk() {
    final FeatureConfiguration featureConfiguration =
        new FeatureConfiguration("my.great.feature", true);
    final FeatureDao featureDao = new InMemoryFeatureDao(featureConfiguration);

    // WHEN
    final Collection<FeatureConfiguration> result = featureDao.getAll();

    // THEN
    Assertions.assertEquals(1, result.size());
    Assertions.assertEquals(featureConfiguration, new ArrayList<>(result).get(0));
  }
}

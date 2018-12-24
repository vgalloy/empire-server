package com.vgalloy.empire.feature.internal.common.store;

import com.vgalloy.empire.feature.api.FeatureDao;
import com.vgalloy.empire.feature.api.FeatureManager;
import com.vgalloy.empire.feature.internal.common.FeatureAdderEnabler;
import com.vgalloy.empire.feature.internal.common.FeatureAdderNoop;
import com.vgalloy.empire.feature.internal.common.FeatureConfiguration;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Vincent Galloy on 13/10/18.
 *
 * @author Vincent Galloy
 */
public final class InMemoryFeatureConfigurationStoreTest {

  @Test
  public void noFeatureAdded() {
    // GIVEN
    final FeatureDao featureDao = new InMemoryFeatureDao();
    final FeatureManager store = new StandardFeatureManager(FeatureAdderNoop.INSTANCE, featureDao);
    store.isEnable("Noop");

    // WHEN
    final Optional<FeatureConfiguration> result = featureDao.getById("Noop");

    // THEN
    Assert.assertFalse(result.isPresent());
  }

  @Test
  public void featureAddAndEnable() {
    // GIVEN
    final FeatureDao featureDao = new InMemoryFeatureDao();
    final FeatureManager store = new StandardFeatureManager(new FeatureAdderEnabler(), featureDao);
    store.isEnable("Noop");

    // WHEN
    final Optional<FeatureConfiguration> result = featureDao.getById("Noop");

    // THEN
    Assert.assertTrue(result.isPresent());
    Assert.assertTrue(result.get().isEnable());
  }
}

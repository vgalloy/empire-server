package com.vgalloy.empire.feature.api.config;

import com.vgalloy.empire.feature.internal.common.store.InMemoryFeatureDao;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Vincent Galloy on 13/10/18.
 *
 * @author Vincent Galloy
 */
public final class FeatureSwitcherModuleBuilderTest {

  @Test
  public void inMemoryBuilder() {
    // WHEN
    final var configuration =
        FeatureSwitcherModuleConfiguration.builder().inMemoryFeatureDao().buildManager().build();

    // THEN
    Assert.assertTrue(configuration.getFeatureDao() instanceof InMemoryFeatureDao);
  }

  @Test
  public void noBuildThrowException() {
    // WHEN / THEN
    Assertions.assertThatThrownBy(() -> FeatureSwitcherModuleConfiguration.builder().build())
        .isInstanceOf(NullPointerException.class)
        .hasMessage("No feature dao defined");
  }
}

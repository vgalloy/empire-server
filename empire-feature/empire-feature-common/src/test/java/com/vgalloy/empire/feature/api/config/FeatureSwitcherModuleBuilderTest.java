package com.vgalloy.empire.feature.api.config;

import com.vgalloy.empire.feature.internal.common.store.InMemoryFeatureDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Vincent Galloy on 13/10/18.
 *
 * @author Vincent Galloy
 */
public final class FeatureSwitcherModuleBuilderTest {

  @Test
  void inMemoryBuilder() {
    // WHEN
    final var configuration =
        FeatureSwitcherModuleConfiguration.builder().inMemoryFeatureDao().buildManager().build();

    // THEN
    Assertions.assertTrue(configuration.getFeatureDao() instanceof InMemoryFeatureDao);
  }

  @Test
  void noBuildThrowException() {
    // WHEN
    final var exception =
        Assertions.assertThrows(
            NullPointerException.class, () -> FeatureSwitcherModuleConfiguration.builder().build());

    // THEN
    Assertions.assertEquals("No feature dao defined", exception.getMessage());
  }
}

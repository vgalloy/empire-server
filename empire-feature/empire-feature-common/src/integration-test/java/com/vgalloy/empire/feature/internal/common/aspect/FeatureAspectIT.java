package com.vgalloy.empire.feature.internal.common.aspect;

import com.vgalloy.empire.feature.api.FeatureDao;
import com.vgalloy.empire.feature.api.FeatureManager;
import com.vgalloy.empire.feature.internal.common.FeatureAdderNoop;
import com.vgalloy.empire.feature.internal.common.FeatureConfiguration;
import com.vgalloy.empire.feature.internal.common.store.InMemoryFeatureDao;
import com.vgalloy.empire.feature.internal.common.store.StandardFeatureManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Created by Vincent Galloy on 11/10/18.
 *
 * @author Vincent Galloy
 */
@SpringBootTest(classes = FeatureAspectIT.Config.class)
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class FeatureAspectIT {

  @Autowired private SampleBean sampleBean;

  @Test
  void applyFeatureDisableShouldBeUsed() {
    // WHEN
    sampleBean.featureEnable();

    // THEN
    Assertions.assertTrue(sampleBean.isUsed());
  }

  @Test
  void applyFeatureDisableShouldNotBeUsed() {
    // WHEN
    sampleBean.featureDisable();

    // THEN
    Assertions.assertFalse(sampleBean.isUsed());
  }

  @Test
  void applyFeatureMissingShouldNotBeUsed() {
    // WHEN
    sampleBean.featureMissing();

    // THEN
    Assertions.assertFalse(sampleBean.isUsed());
  }

  @Configuration
  @EnableAspectJAutoProxy
  public static class Config {

    @Bean
    FeatureDao featureDao() {
      return new InMemoryFeatureDao(
          new FeatureConfiguration(SampleBean.FEATURE_OK, true),
          new FeatureConfiguration(SampleBean.FEATURE_KO, false));
    }

    @Bean
    FeatureManager featureConfigurationStore(final FeatureDao featureDao) {
      return new StandardFeatureManager(FeatureAdderNoop.INSTANCE, featureDao);
    }

    @Bean
    FeatureAspect featureAspect(final FeatureManager featureManager) {
      return new FeatureAspect(featureManager);
    }

    @Bean
    SampleBean sampleBean() {
      return new SampleBean();
    }
  }
}

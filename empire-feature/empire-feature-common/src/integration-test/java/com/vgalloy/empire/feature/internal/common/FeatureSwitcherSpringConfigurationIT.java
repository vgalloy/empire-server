package com.vgalloy.empire.feature.internal.common;

import com.vgalloy.empire.feature.api.EnableFeatureSwitcher;
import com.vgalloy.empire.feature.api.FeatureDao;
import com.vgalloy.empire.feature.internal.common.store.InMemoryFeatureDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Created by Vincent Galloy on 17/01/19.
 *
 * @author Vincent Galloy
 */
@Import(FeatureSwitcherSpringConfigurationIT.Config.class)
@ExtendWith(SpringExtension.class)
public class FeatureSwitcherSpringConfigurationIT {

  @Autowired private ApplicationContext applicationContext;

  @Test
  public void checkCorrectionInitialization() {
    Assertions.assertNotNull(applicationContext);
    Assertions.assertNotNull(applicationContext.getBean(FeatureDao.class));
  }

  @EnableFeatureSwitcher
  @SpringBootConfiguration
  public static class Config {

    @Bean
    public FeatureDao featureDao() {
      return new InMemoryFeatureDao(new FeatureConfiguration("test", true));
    }
  }
}

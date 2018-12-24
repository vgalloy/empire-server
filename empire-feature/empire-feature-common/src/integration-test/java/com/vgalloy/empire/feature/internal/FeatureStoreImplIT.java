package com.vgalloy.empire.feature.internal;

import com.vgalloy.empire.feature.api.FeatureStore;
import com.vgalloy.empire.feature.internal.common.ApplicationProperties;
import com.vgalloy.empire.feature.internal.common.FeatureStoreImpl;
import com.vgalloy.empire.feature.internal.sample.Operation;
import com.vgalloy.empire.feature.internal.sample.TestConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Created by Vincent Galloy on 07/10/18.
 *
 * @author Vincent Galloy
 */
@SpringBootTest(classes = FeatureStoreImplIT.Config.class)
@ExtendWith(SpringExtension.class)
public class FeatureStoreImplIT {

  @Autowired private FeatureStore featureStore;

  @Test
  void operation() {
    // WHEN
    final Operation operation = featureStore.loadFeature(Operation.class);

    // THEN
    Assertions.assertNotNull(operation);
    Assertions.assertEquals(3, operation.apply(1, 2));
  }

  @Configuration
  @Import({ApplicationProperties.class, TestConfig.class})
  public static class Config {

    @Bean
    public FeatureStore featureStore(
        final ApplicationContext applicationContext,
        final ApplicationProperties applicationProperties) {
      return new FeatureStoreImpl(applicationContext, applicationProperties);
    }
  }
}

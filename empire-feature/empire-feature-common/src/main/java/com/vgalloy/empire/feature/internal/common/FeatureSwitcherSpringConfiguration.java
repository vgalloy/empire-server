package com.vgalloy.empire.feature.internal.common;

import com.vgalloy.empire.feature.api.FeatureAdder;
import com.vgalloy.empire.feature.api.FeatureDao;
import com.vgalloy.empire.feature.api.FeatureManager;
import com.vgalloy.empire.feature.internal.common.aspect.FeatureAspect;
import com.vgalloy.empire.feature.internal.common.store.InMemoryFeatureDao;
import com.vgalloy.empire.feature.internal.common.store.StandardFeatureManager;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Vincent Galloy on 12/10/18.
 *
 * @author Vincent Galloy
 */
@Configuration
public class FeatureSwitcherSpringConfiguration {

  private final FeatureDao featureDao;
  private final FeatureAdder featureAdder;
  private final FeatureManager featureManager;

  /**
   * Constructor.
   *
   * @param featureDao the featureDao
   * @param featureAdder the featureAdder
   * @param featureManager the featureManager
   */
  public FeatureSwitcherSpringConfiguration(
      @Autowired(required = false) final FeatureDao featureDao,
      @Autowired(required = false) final FeatureAdder featureAdder,
      @Autowired(required = false) final FeatureManager featureManager) {
    if (Objects.isNull(featureDao)) {
      this.featureDao = new InMemoryFeatureDao();
    } else {
      this.featureDao = featureDao;
    }

    if (Objects.isNull(featureAdder)) {
      this.featureAdder = new FeatureAdderEnabler();
    } else {
      this.featureAdder = featureAdder;
    }

    if (Objects.isNull(featureManager)) {
      this.featureManager = new StandardFeatureManager(this.featureAdder, this.featureDao);
    } else {
      this.featureManager = featureManager;
    }
  }

  /**
   * Build Spring bean.
   *
   * @return the bean
   */
  @Bean
  public FeatureAspect featureAspect() {
    return new FeatureAspect(featureManager);
  }

  public FeatureDao getFeatureDao() {
    return featureDao;
  }

  public FeatureAdder getFeatureAdder() {
    return featureAdder;
  }

  public FeatureManager getFeatureManager() {
    return featureManager;
  }
}

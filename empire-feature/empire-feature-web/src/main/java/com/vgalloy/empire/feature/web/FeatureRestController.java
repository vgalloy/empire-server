package com.vgalloy.empire.feature.web;

import com.vgalloy.empire.feature.api.FeatureDao;
import com.vgalloy.empire.feature.internal.common.FeatureConfiguration;
import com.vgalloy.empire.feature.internal.common.FeatureSwitcherSpringConfiguration;
import com.vgalloy.empire.feature.web.exception.WebNotFoundException;
import java.util.Collection;
import java.util.Objects;
import javax.servlet.http.HttpServlet;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Vincent Galloy on 11/10/18.
 *
 * @author Vincent Galloy
 */
@ConditionalOnClass(HttpServlet.class)
@RequestMapping(WebConstant.FEATURE_API + "/features")
@RestController
public class FeatureRestController {

  private final FeatureDao featureDao;

  /**
   * Constructor.
   *
   * @param featureSwitcherSpringConfiguration base feature configuration, not null
   */
  public FeatureRestController(
      final FeatureSwitcherSpringConfiguration featureSwitcherSpringConfiguration) {
    this.featureDao = Objects.requireNonNull(featureSwitcherSpringConfiguration.getFeatureDao());
  }

  /**
   * Get all feature.
   *
   * @return all available feature
   */
  @GetMapping
  public Collection<FeatureConfiguration> getAll() {
    return featureDao.getAll();
  }

  /**
   * Get one feature by its id.
   *
   * @param featureId feature id, not null
   * @return the feature
   */
  @GetMapping("{featureId}")
  public FeatureConfiguration getById(final @PathVariable String featureId) {
    return featureDao.getById(featureId).orElseThrow(() -> new WebNotFoundException(featureId));
  }

  /**
   * Update one feature.
   *
   * @param featureId the feature id, not null
   * @param featureConfiguration the new configuration, not null
   * @return the updated configuration
   */
  @PutMapping("{featureId}")
  public FeatureConfiguration update(
      final @PathVariable String featureId,
      final @RequestBody FeatureConfiguration featureConfiguration) {
    // TODO
    final var newfeature = new FeatureConfiguration(featureId, featureConfiguration.isEnable());
    return featureDao.update(newfeature);
  }
}

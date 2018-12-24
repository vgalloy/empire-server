package com.vgalloy.empire.feature.api;

import com.vgalloy.empire.feature.internal.common.FeatureConfiguration;
import java.util.Optional;

/**
 * Created by Vincent Galloy on 13/10/18.
 *
 * @author Vincent Galloy
 */
public interface FeatureAdder {

  /**
   * Define the behavior when a feature not present in the store is require.
   *
   * @param featureName the feature name, not null
   * @return Optional empty if new feature must not be add into the store or the corresponding
   *     feature
   */
  Optional<FeatureConfiguration> addFeature(String featureName);
}

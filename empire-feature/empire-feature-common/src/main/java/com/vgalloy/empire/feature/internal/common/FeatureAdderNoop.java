package com.vgalloy.empire.feature.internal.common;

import com.vgalloy.empire.feature.api.FeatureAdder;
import java.util.Optional;

/**
 * Created by Vincent Galloy on 13/10/18.
 *
 * @author Vincent Galloy
 */
public class FeatureAdderNoop implements FeatureAdder {

  public static final FeatureAdderNoop INSTANCE = new FeatureAdderNoop();

  @Override
  public Optional<FeatureConfiguration> addFeature(final String featureName) {
    return Optional.empty();
  }
}

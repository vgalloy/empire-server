package com.vgalloy.empire.feature.internal.common;

import com.vgalloy.empire.feature.api.FeatureAdder;
import java.lang.invoke.MethodHandles;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Vincent Galloy on 13/10/18.
 *
 * @author Vincent Galloy
 */
public class FeatureAdderEnabler implements FeatureAdder {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

  @Override
  public Optional<FeatureConfiguration> addFeature(final String featureName) {
    Objects.requireNonNull(featureName, "featureName");
    LOGGER.info("Discover feature {} and add it to the store", featureName);
    return Optional.of(new FeatureConfiguration(featureName, true));
  }
}

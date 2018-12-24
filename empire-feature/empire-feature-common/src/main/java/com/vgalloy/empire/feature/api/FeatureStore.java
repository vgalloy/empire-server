package com.vgalloy.empire.feature.api;

/**
 * Created by Vincent Galloy on 07/10/18.
 *
 * @author Vincent Galloy
 */
public interface FeatureStore {

  /**
   * Provide the correct implementation for the given feature. The feature must be a {@link
   * Feature}.
   *
   * @param featureClass the feature class
   * @param <T> the feature
   * @return the implementation match with key provide by application.properties
   */
  <T> T loadFeature(Class<T> featureClass);

  /**
   * Provide the correct implementation for the given feature. The feature must be a {@link
   * Feature}.
   *
   * @param featureClass the feature class
   * @param implementationKey the specific key
   * @param <T> the feature
   * @return the implementation match with key
   */
  <T> T loadFeature(Class<T> featureClass, String implementationKey);
}

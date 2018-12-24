package com.vgalloy.empire.feature.internal.common;

import com.vgalloy.empire.feature.api.Feature;
import com.vgalloy.empire.feature.api.FeatureStore;
import java.util.Objects;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * Created by Vincent Galloy on 07/10/18.
 *
 * @author Vincent Galloy
 */
@Component
public class FeatureStoreImpl implements FeatureStore {

  private final ApplicationContext applicationContext;
  private final ApplicationProperties applicationProperties;

  /**
   * Constructor.
   *
   * @param applicationContext the applicationContext
   * @param applicationProperties the features
   */
  public FeatureStoreImpl(
      final ApplicationContext applicationContext,
      final ApplicationProperties applicationProperties) {
    this.applicationContext = Objects.requireNonNull(applicationContext);
    this.applicationProperties = Objects.requireNonNull(applicationProperties);
  }

  @Override
  public <T> T loadFeature(final Class<T> featureClass) {
    Objects.requireNonNull(featureClass, "featureClass");

    final var featureKey = extractAndValidateFeatureKey(featureClass);
    final var implementationKey = applicationProperties.getFeatures().get(featureKey);
    Assert.notNull(implementationKey, "Enable to find features." + featureKey + " in properties");

    return loadFeature(featureClass, implementationKey);
  }

  @Override
  public <T> T loadFeature(final Class<T> featureClass, final String implementationKey) {
    Objects.requireNonNull(featureClass, "featureClass");
    Objects.requireNonNull(implementationKey, "implementationKey");

    final var implementationExtractor =
        new ImplementationExtractor<>(applicationContext.getBeansOfType(featureClass).values());
    return implementationExtractor.getImplementation(implementationKey);
  }

  /**
   * Extract and validate the value for the annotation {@link Feature}.
   *
   * @param featureClass the class with the annotation
   * @return the value, not null, not empty
   */
  private String extractAndValidateFeatureKey(final Class<?> featureClass) {
    final var feature = featureClass.getAnnotation(Feature.class);
    Assert.notNull(
        feature,
        featureClass.getCanonicalName()
            + " is not annotated with @"
            + Feature.class.getSimpleName());
    if (feature.value().isEmpty()) {
      return featureClass.getCanonicalName();
    }
    return feature.value();
  }
}

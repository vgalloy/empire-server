package com.vgalloy.empire.feature.internal.common;

import com.vgalloy.empire.feature.api.Implementation;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.util.Assert;

/**
 * Created by Vincent Galloy on 07/10/18.
 *
 * @author Vincent Galloy
 */
final class ImplementationExtractor<T> {

  private final Collection<T> implementation;

  /**
   * Constructor.
   *
   * @param implementation the list of available implementation
   */
  ImplementationExtractor(final Collection<T> implementation) {
    this.implementation = Objects.requireNonNull(implementation, "implementation");
  }

  /**
   * Extract the correct implementation.
   *
   * @param implementationKey the key
   * @return the implementation matching with the key
   */
  T getImplementation(final String implementationKey) {
    Objects.requireNonNull(implementationKey, "implementationKey");

    final var features =
        implementation
            .stream()
            .filter(impl -> this.filterImplementation(implementationKey, impl))
            .collect(Collectors.toList());

    Assert.state(!features.isEmpty(), "No @Implementation with value : " + implementationKey);
    Assert.state(
        features.size() < 2, "Multiple implementation get the same key : " + implementationKey);
    return features.get(0);
  }

  /**
   * Extract the {@link Implementation} value.
   *
   * @param implementation the implementation
   * @return {@link Optional#empty()} if the provided implementation is not annotated with {@link
   *     Implementation}
   */
  private Optional<String> extractImplementationKey(final Object implementation) {
    final var implementationClass = implementation.getClass();
    final var implementationAnnotation = implementationClass.getAnnotation(Implementation.class);
    if (Objects.isNull(implementationAnnotation)) {
      return Optional.empty();
    }
    if (implementationAnnotation.value().isEmpty()) {
      return Optional.of(implementationClass.getCanonicalName());
    }
    return Optional.of(implementationAnnotation.value());
  }

  /**
   * Checks if {@link Implementation} value match with the provided key.
   *
   * @param key the implementation key
   * @param implementation the implemenation object
   * @return true if the object {@link Implementation} value match with the provided key
   */
  private boolean filterImplementation(final String key, final Object implementation) {
    final var implementationKey = extractImplementationKey(implementation);
    return implementationKey.filter(s -> Objects.equals(key, s)).isPresent();
  }
}

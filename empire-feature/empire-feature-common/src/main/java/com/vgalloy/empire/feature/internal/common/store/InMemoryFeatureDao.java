package com.vgalloy.empire.feature.internal.common.store;

import com.vgalloy.empire.feature.api.FeatureDao;
import com.vgalloy.empire.feature.internal.common.FeatureConfiguration;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.util.Assert;

/**
 * Created by Vincent Galloy on 11/10/18.
 *
 * @author Vincent Galloy
 */
public class InMemoryFeatureDao implements FeatureDao {

  private final Map<String, FeatureConfiguration> map = new ConcurrentHashMap<>();

  /**
   * Constructor.
   *
   * @param featureConfigurations the initial configuration, not null
   */
  public InMemoryFeatureDao(final FeatureConfiguration... featureConfigurations) {
    for (final var featureConfiguration : featureConfigurations) {
      map.put(featureConfiguration.getName(), featureConfiguration);
    }
  }

  @Override
  public Optional<FeatureConfiguration> getById(final String featureId) {
    Objects.requireNonNull(featureId);
    return Optional.ofNullable(map.get(featureId));
  }

  @Override
  public FeatureConfiguration update(final FeatureConfiguration featureConfiguration) {
    Objects.requireNonNull(featureConfiguration);
    Assert.state(
        map.containsKey(featureConfiguration.getName()),
        "Can't update an non existing feature configuration");
    map.put(featureConfiguration.getName(), featureConfiguration);
    return featureConfiguration;
  }

  @Override
  public FeatureConfiguration add(final FeatureConfiguration featureConfiguration) {
    Objects.requireNonNull(featureConfiguration);
    Assert.state(
        !map.containsKey(featureConfiguration.getName()),
        "Can create a exiting feature configuration");
    map.put(featureConfiguration.getName(), featureConfiguration);
    return featureConfiguration;
  }

  @Override
  public Collection<FeatureConfiguration> getAll() {
    return map.values();
  }
}

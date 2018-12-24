package com.vgalloy.empire.feature.internal;

import com.vgalloy.empire.feature.internal.common.ApplicationProperties;
import com.vgalloy.empire.feature.internal.common.FeatureStoreImpl;
import com.vgalloy.empire.feature.internal.operation.AddOperation;
import com.vgalloy.empire.feature.internal.operation.FakeOperation;
import com.vgalloy.empire.feature.internal.operation.MinusOperation;
import com.vgalloy.empire.feature.internal.operation.Operation;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.context.ApplicationContext;

/**
 * Created by Vincent Galloy on 07/10/18.
 *
 * @author Vincent Galloy
 */
public final class FeatureStoreImplTest {

  @Test
  void correctCaseWithParameter() {
    // GIVEN
    final var applicationProperties = new ApplicationProperties();
    final var applicationContext = BDDMockito.mock(ApplicationContext.class);
    final var featureStore = new FeatureStoreImpl(applicationContext, applicationProperties);
    final Map<String, Operation> operationMap = new HashMap<>();

    BDDMockito.given(applicationContext.getBeansOfType(Operation.class)).willReturn(operationMap);
    applicationProperties.setFeatures(new HashMap<>());
    applicationProperties.getFeatures().put("operation", "add");
    operationMap.put(AddOperation.class.getName(), new AddOperation());
    operationMap.put(MinusOperation.class.getName(), new MinusOperation());

    // WHEN
    final var operation = featureStore.loadFeature(Operation.class);

    // THEN
    Assertions.assertNotNull(operation);
    Assertions.assertEquals(AddOperation.class, operation.getClass());
  }

  @Test
  void correctCaseWithClassName() {
    // GIVEN
    final var applicationProperties = new ApplicationProperties();
    final var applicationContext = BDDMockito.mock(ApplicationContext.class);
    final var featureStore = new FeatureStoreImpl(applicationContext, applicationProperties);
    final Map<String, Operation> operationMap = new HashMap<>();

    BDDMockito.given(applicationContext.getBeansOfType(Operation.class)).willReturn(operationMap);
    applicationProperties.setFeatures(new HashMap<>());
    applicationProperties.getFeatures().put("operation", MinusOperation.class.getCanonicalName());
    operationMap.put(AddOperation.class.getName(), new AddOperation());
    operationMap.put(MinusOperation.class.getName(), new MinusOperation());

    // WHEN
    final var operation = featureStore.loadFeature(Operation.class);

    // THEN
    Assertions.assertNotNull(operation);
    Assertions.assertEquals(MinusOperation.class, operation.getClass());
  }

  @Test
  void notAFeatureClass() {
    // GIVEN
    final var applicationProperties = new ApplicationProperties();
    final var applicationContext = BDDMockito.mock(ApplicationContext.class);
    final var featureStore = new FeatureStoreImpl(applicationContext, applicationProperties);

    // EXCEPTION
    final var exception =
        Assertions.assertThrows(
            IllegalArgumentException.class, () -> featureStore.loadFeature(FakeOperation.class));

    // THEN
    Assertions.assertEquals(
        "com.vgalloy.empire.feature.internal.operation.FakeOperation is not annotated with @Feature",
        exception.getMessage());
  }

  @Test
  void noClassAvailable() {
    // GIVEN
    final var applicationProperties = new ApplicationProperties();
    final var applicationContext = BDDMockito.mock(ApplicationContext.class);
    final var featureStore = new FeatureStoreImpl(applicationContext, applicationProperties);
    final Map<String, Operation> operationMap = new HashMap<>();

    BDDMockito.given(applicationContext.getBeansOfType(Operation.class)).willReturn(operationMap);
    operationMap.put(AddOperation.class.getName(), new AddOperation());

    // EXCEPTION
    final var exception =
        Assertions.assertThrows(
            IllegalStateException.class,
            () ->
                featureStore.loadFeature(Operation.class, MinusOperation.class.getCanonicalName()));

    // WHEN
    Assertions.assertEquals(
        "No @Implementation with value : com.vgalloy.empire.feature.internal.operation.MinusOperation",
        exception.getMessage());
  }
}

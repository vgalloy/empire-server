package com.vgalloy.empire.feature.internal;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.BDDMockito;
import org.springframework.context.ApplicationContext;

import com.vgalloy.empire.feature.api.FeatureStore;
import com.vgalloy.empire.feature.internal.operation.AddOperation;
import com.vgalloy.empire.feature.internal.operation.FakeOperation;
import com.vgalloy.empire.feature.internal.operation.MinusOperation;
import com.vgalloy.empire.feature.internal.operation.Operation;

/**
 * Created by Vincent Galloy on 07/10/18.
 *
 * @author Vincent Galloy
 */
public final class FeatureStoreImplTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void correctCaseWithParameter() {
        // GIVEN
        final ApplicationProperties applicationProperties = new ApplicationProperties();
        final ApplicationContext applicationContext = BDDMockito.mock(ApplicationContext.class);
        final FeatureStore featureStore = new FeatureStoreImpl(applicationContext, applicationProperties);
        final Map<String, Operation> operationMap = new HashMap<>();

        BDDMockito.given(applicationContext.getBeansOfType(Operation.class)).willReturn(operationMap);
        applicationProperties.setFeatures(new HashMap<>());
        applicationProperties.getFeatures().put("operation", "add");
        operationMap.put(AddOperation.class.getName(), new AddOperation());
        operationMap.put(MinusOperation.class.getName(), new MinusOperation());

        // WHEN
        final Operation operation = featureStore.loadFeature(Operation.class);

        // THEN
        Assert.assertNotNull(operation);
        Assert.assertEquals(AddOperation.class, operation.getClass());
    }

    @Test
    public void correctCaseWithClassName() {
        // GIVEN
        final ApplicationProperties applicationProperties = new ApplicationProperties();
        final ApplicationContext applicationContext = BDDMockito.mock(ApplicationContext.class);
        final FeatureStore featureStore = new FeatureStoreImpl(applicationContext, applicationProperties);
        final Map<String, Operation> operationMap = new HashMap<>();

        BDDMockito.given(applicationContext.getBeansOfType(Operation.class)).willReturn(operationMap);
        applicationProperties.setFeatures(new HashMap<>());
        applicationProperties.getFeatures().put("operation", MinusOperation.class.getCanonicalName());
        operationMap.put(AddOperation.class.getName(), new AddOperation());
        operationMap.put(MinusOperation.class.getName(), new MinusOperation());

        // WHEN
        final Operation operation = featureStore.loadFeature(Operation.class);

        // THEN
        Assert.assertNotNull(operation);
        Assert.assertEquals(MinusOperation.class, operation.getClass());
    }

    @Test
    public void notAFeatureClass() {
        // GIVEN
        final ApplicationProperties applicationProperties = new ApplicationProperties();
        final ApplicationContext applicationContext = BDDMockito.mock(ApplicationContext.class);
        final FeatureStore featureStore = new FeatureStoreImpl(applicationContext, applicationProperties);

        // EXCEPTION
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("com.vgalloy.empire.feature.internal.operation.FakeOperation is not annotated with @Feature");

        // WHEN
        featureStore.loadFeature(FakeOperation.class);
    }

    @Test
    public void noClassAvailable() {
        // GIVEN
        final ApplicationProperties applicationProperties = new ApplicationProperties();
        final ApplicationContext applicationContext = BDDMockito.mock(ApplicationContext.class);
        final FeatureStore featureStore = new FeatureStoreImpl(applicationContext, applicationProperties);
        final Map<String, Operation> operationMap = new HashMap<>();

        BDDMockito.given(applicationContext.getBeansOfType(Operation.class)).willReturn(operationMap);
        operationMap.put(AddOperation.class.getName(), new AddOperation());

        // EXCEPTION
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("No @Implementation with value : com.vgalloy.empire.feature.internal.operation.MinusOperation");

        // WHEN
        featureStore.loadFeature(Operation.class, MinusOperation.class.getCanonicalName());
    }
}
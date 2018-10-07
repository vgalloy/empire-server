package com.vgalloy.empire.feature.internal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import com.vgalloy.empire.feature.api.FeatureStore;

/**
 * Created by Vincent Galloy on 07/10/18.
 *
 * @author Vincent Galloy
 */
@SpringBootTest(classes = FeatureStoreImplIT.ConfigTest.class)
//@Import(FeatureStoreImplIT.ConfigTest.class)
@RunWith(SpringRunner.class)
public class FeatureStoreImplIT {

    @ComponentScan
    public static class ConfigTest {

    }

    @Autowired
    private FeatureStore featureStore;

    @Test
    public void operation() {
        // WHEN
        final Ops operation = featureStore.loadFeature(Ops.class);

        // THEN
        Assert.assertNotNull(operation);
        Assert.assertEquals(3, operation.apply(1, 2));
    }
}
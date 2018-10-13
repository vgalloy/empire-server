package com.vgalloy.empire.feature.internal.common.store;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.vgalloy.empire.feature.api.FeatureManager;
import com.vgalloy.empire.feature.internal.common.FeatureAdderNoop;
import com.vgalloy.empire.feature.internal.common.FeatureConfiguration;
import com.vgalloy.empire.feature.internal.common.aspect.FeatureAspect;

/**
 * Created by Vincent Galloy on 11/10/18.
 *
 * @author Vincent Galloy
 */
@SpringBootTest(classes = InMemoryFeatureConfigurationStoreIT.Config.class)
@RunWith(SpringRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class InMemoryFeatureConfigurationStoreIT {

    @Autowired
    private SampleBean sampleBean;

    @Test
    public void applyFeatureDisableShouldBeUsed() {
        // WHEN
        sampleBean.featureEnable();

        // THEN
        Assert.assertTrue(sampleBean.isUsed());
    }

    @Test
    public void applyFeatureDisableShouldNotBeUsed() {
        // WHEN
        sampleBean.featureDisable();

        // THEN
        Assert.assertFalse(sampleBean.isUsed());
    }

    @Test
    public void applyFeatureMissingShouldNotBeUsed() {
        // WHEN
        sampleBean.featureMissing();

        // THEN
        Assert.assertFalse(sampleBean.isUsed());
    }

    @Configuration
    @EnableAspectJAutoProxy
    public static class Config {

        @Bean
        public FeatureManager featureConfigurationStore() {
            return new InMemoryFeatureManager(FeatureAdderNoop.INSTANCE,
                new FeatureConfiguration(SampleBean.FEATURE_OK, true),
                new FeatureConfiguration(SampleBean.FEATURE_KO, false)
            );
        }

        @Bean
        public FeatureAspect featureAspect(final FeatureManager featureManager) {
            return new FeatureAspect(featureManager);
        }

        @Bean
        public SampleBean sampleBean() {
            return new SampleBean();
        }
    }
}
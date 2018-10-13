package com.vgalloy.empire.feature.web;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import com.vgalloy.empire.feature.api.EnableFeatureSwitcher;
import com.vgalloy.empire.feature.api.FeatureManager;
import com.vgalloy.empire.feature.internal.common.FeatureAdderNoop;
import com.vgalloy.empire.feature.internal.common.FeatureConfiguration;
import com.vgalloy.empire.feature.internal.common.store.InMemoryFeatureManager;

/**
 * Created by Vincent Galloy on 13/10/18.
 *
 * @author Vincent Galloy
 */
@RunWith(SpringRunner.class)
@Import(FeatureRestControllerIT.Config.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FeatureRestControllerIT {

    private static final String FEATURE_NAME = "web.feature";
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getOneFeature() {
        // GIVEN
        final String nonExistingUrl = WebConstant.FEATURE_API + "/features/" + FEATURE_NAME;

        // WHEN
        final FeatureConfiguration result = restTemplate.getForObject(nonExistingUrl, FeatureConfiguration.class);

        // THEN
        Assert.assertNotNull(result);
        Assert.assertEquals(FEATURE_NAME, result.getName());
        Assert.assertTrue(result.isEnable());
    }

    @EnableFeatureSwitcher
    @SpringBootConfiguration
    @EnableAutoConfiguration
    public static class Config {
        @Bean
        public FeatureManager featureConfigurationStore() {
            return new InMemoryFeatureManager(FeatureAdderNoop.INSTANCE, new FeatureConfiguration(FEATURE_NAME, true));
        }

        @Bean
        public FeatureRestController FeatureRestController(final FeatureManager featureManager) {
            return new FeatureRestController(featureManager);
        }
    }
}
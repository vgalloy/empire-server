package com.vgalloy.empire.feature.internal.web;

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
import com.vgalloy.empire.feature.internal.common.FeatureConfiguration;
import com.vgalloy.empire.feature.internal.common.store.FeatureConfigurationStore;
import com.vgalloy.empire.feature.internal.common.store.InMemoryFeatureConfigurationStore;

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

    @EnableFeatureSwitcher
    @SpringBootConfiguration
    @EnableAutoConfiguration
    public static class Config {

        @Bean
        public FeatureConfigurationStore featureConfigurationStore() {
            return new InMemoryFeatureConfigurationStore(new FeatureConfiguration(FEATURE_NAME, true));
        }
    }

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getOneFeature() {
        // GIVEN
        final String nonExistingUrl = "/features/" + FEATURE_NAME;

        // WHEN
        final FeatureConfiguration result = restTemplate.getForObject(nonExistingUrl, FeatureConfiguration.class);

        // THEN
        Assert.assertNotNull(result);
        Assert.assertEquals(FEATURE_NAME, result.getName());
        Assert.assertTrue(result.isEnable());
    }
}
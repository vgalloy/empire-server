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
import org.springframework.test.context.junit4.SpringRunner;

import com.vgalloy.empire.feature.api.EnableFeatureSwitcher;
import com.vgalloy.empire.feature.api.FeatureDao;
import com.vgalloy.empire.feature.api.FeatureManager;
import com.vgalloy.empire.feature.internal.common.FeatureAdderNoop;
import com.vgalloy.empire.feature.internal.common.FeatureConfiguration;
import com.vgalloy.empire.feature.internal.common.store.InMemoryFeatureDao;
import com.vgalloy.empire.feature.internal.common.store.StandardFeatureManager;

/**
 * Created by Vincent Galloy on 13/10/18.
 *
 * @author Vincent Galloy
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = FeatureRestControllerIT.Config.class)
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
        FeatureDao featureDao() {
            return new InMemoryFeatureDao(new FeatureConfiguration(FEATURE_NAME, true));
        }

        @Bean
        FeatureManager featureConfigurationStore(final FeatureDao featureDao) {
            return new StandardFeatureManager(FeatureAdderNoop.INSTANCE, featureDao);
        }

        @Bean
        FeatureRestController FeatureRestController(final FeatureDao featureDao) {
            return new FeatureRestController(featureDao);
        }
    }
}
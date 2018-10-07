package com.vgalloy.empire.feature.internal;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by Vincent Galloy on 07/10/18.
 *
 * @author Vincent Galloy
 */
@EnableConfigurationProperties(ApplicationProperties.class)
@Configuration
@PropertySource("classpath:application.yml")
@ConfigurationProperties
public class ApplicationProperties {

    private Map<String, String> features;

    public Map<String, String> getFeatures() {
        return features;
    }

    public void setFeatures(final Map<String, String> features) {
        this.features = features;
    }
}

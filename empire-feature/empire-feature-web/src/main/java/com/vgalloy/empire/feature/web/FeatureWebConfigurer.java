package com.vgalloy.empire.feature.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Vincent Galloy on 20/10/18.
 *
 * @author Vincent Galloy
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
public class FeatureWebConfigurer implements WebMvcConfigurer {

    private static final String FEATURE_RESOURCE_LOCATION = "/static/feature/";
    private static final String REACT_JS = "static/js/";

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry
            .addResourceHandler("/static/js/**")
            .addResourceLocations("classpath:" + FEATURE_RESOURCE_LOCATION + REACT_JS);
        registry
            .addResourceHandler(WebConstant.FEATURE_WEB + "/**")
            .addResourceLocations("classpath:" + FEATURE_RESOURCE_LOCATION);
    }
}

package com.vgalloy.empire.feature.internal.sample;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Vincent Galloy on 11/10/18.
 *
 * @author Vincent Galloy
 */
@Configuration
public class TestConfig {

    @Bean
    public Operation addOperation() {
        return new AddOperation();
    }

    @Bean
    public Operation minusOperation() {
        return new MinusOperation();
    }
}

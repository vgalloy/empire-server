package com.vgalloy.empire.common.log;

import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Vincent Galloy on 01/05/18.
 *
 * @author Vincent Galloy
 */
@Configuration
public class LoggerFactoryConfig {

    /**
     * Inject the slf4j logger factory into the spring context.
     *
     * @return the factory
     */
    @Bean
    public ILoggerFactory loggerFactory() {
        return LoggerFactory.getILoggerFactory();
    }
}

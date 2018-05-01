package com.vgalloy.empire.webservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class, scanBasePackages = "com.vgalloy.empire")
public class ServerConfig {

    /**
     * The main method for Spring boot.
     *
     * @param args the args
     */
    public static void main(final String[] args) {
        new SpringApplicationBuilder()
            .sources(ServerConfig.class)
            .run(args);
    }
}

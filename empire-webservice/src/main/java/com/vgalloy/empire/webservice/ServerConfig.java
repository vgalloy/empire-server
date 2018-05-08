package com.vgalloy.empire.webservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(scanBasePackages = "com.vgalloy.empire")
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

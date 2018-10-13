package com.vgalloy.empire.webservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(scanBasePackages = {
    "com.vgalloy.empire.webservice",
    "com.vgalloy.empire.common",
    "com.vgalloy.empire.service",
    "com.vgalloy.empire.persistence",
    "com.vgalloy.empire.i18n"
})
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

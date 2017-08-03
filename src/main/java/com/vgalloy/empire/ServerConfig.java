package com.vgalloy.empire;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
public class ServerConfig {

	public static void main(String[] args) {
		new SpringApplicationBuilder()
			.sources(ServerConfig.class)
			//.bannerMode(Banner.Mode.OFF)
			.run(args);
	}
}

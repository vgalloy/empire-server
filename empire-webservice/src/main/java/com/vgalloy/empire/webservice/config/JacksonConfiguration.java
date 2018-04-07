package com.vgalloy.empire.webservice.config;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Vincent Galloy on 20/08/17.
 *
 * @author Vincent Galloy
 */
@Configuration
public class JacksonConfiguration {

    private final List<StdSerializer<?>> serializers;

    /**
     * Constructor.
     *
     * @param serializers the serializers
     */
    public JacksonConfiguration(List<StdSerializer<?>> serializers) {
        this.serializers = Objects.requireNonNull(serializers);
    }

    /**
     * Create Jackson mapper bean.
     *
     * @return the bean
     */
    @Bean
    public ObjectMapper objectMapper() {
        SimpleModule module = new SimpleModule();
        for (StdSerializer<?> serializer : serializers) {
            module.addSerializer(serializer);
        }

        return new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .registerModule(module);
    }
}

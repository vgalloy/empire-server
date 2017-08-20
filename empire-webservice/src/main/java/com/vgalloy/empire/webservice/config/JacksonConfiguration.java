package com.vgalloy.empire.webservice.config;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vgalloy.empire.webservice.dto.EmpireIdDto;

/**
 * Created by Vincent Galloy on 20/08/17.
 *
 * @author Vincent Galloy
 */
@Configuration
public class JacksonConfiguration {

    private final StdSerializer<EmpireIdDto> empireIdStdSerializer;

    /**
     * Constructor.
     *
     * @param empireIdStdSerializer the empire id converter
     */
    public JacksonConfiguration(StdSerializer<EmpireIdDto> empireIdStdSerializer) {
        this.empireIdStdSerializer = Objects.requireNonNull(empireIdStdSerializer);
    }

    /**
     * Create Jackson mapper bean.
     *
     * @return the bean
     */
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        SimpleModule module = new SimpleModule();
        module.addSerializer(EmpireIdDto.class, empireIdStdSerializer);
        mapper.registerModule(module);

        return mapper;
    }
}

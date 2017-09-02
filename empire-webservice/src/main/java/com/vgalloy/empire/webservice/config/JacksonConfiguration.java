package com.vgalloy.empire.webservice.config;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.vgalloy.empire.webservice.dto.EmpireIdDto;
import com.vgalloy.empire.webservice.dto.UserIdDto;

/**
 * Created by Vincent Galloy on 20/08/17.
 *
 * @author Vincent Galloy
 */
@Configuration
public class JacksonConfiguration {

    private final StdSerializer<EmpireIdDto> empireIdStdSerializer;
    private final StdSerializer<UserIdDto> userIdDtoStdSerializer;

    /**
     * Constructor.
     *
     * @param empireIdStdSerializer the empire id converter
     * @param userIdDtoStdSerializer the user id converter
     */
    public JacksonConfiguration(StdSerializer<EmpireIdDto> empireIdStdSerializer, StdSerializer<UserIdDto> userIdDtoStdSerializer) {
        this.empireIdStdSerializer = Objects.requireNonNull(empireIdStdSerializer);
        this.userIdDtoStdSerializer = Objects.requireNonNull(userIdDtoStdSerializer);
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
        module.addSerializer(UserIdDto.class, userIdDtoStdSerializer);
        mapper.registerModule(module);

        return mapper;
    }
}

package com.vgalloy.empire.webservice.config;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import com.vgalloy.empire.webservice.dto.EmpireIdDto;
import com.vgalloy.empire.webservice.dto.ErrorDto;

/**
 * Create by Vincent Galloy on 02/08/2017.
 *
 * @author Vincent Galloy
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * Swagger Api configuration.
     *
     * @return The Swagger api configuration.
     */
    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
            .directModelSubstitute(LocalDate.class, String.class)
            .directModelSubstitute(EmpireIdDto.class, String.class)
            .genericModelSubstitutes(ResponseEntity.class)
            .securitySchemes(Collections.singletonList(new BasicAuth("")))
            .useDefaultResponseMessages(false)
            .globalResponseMessage(RequestMethod.GET, Arrays.asList(
                new ResponseMessageBuilder().code(200).message("OK").responseModel(new ModelRef("Success")).build(),
                build(HttpStatus.BAD_REQUEST),
                build(HttpStatus.UNAUTHORIZED),
                build(HttpStatus.NOT_FOUND),
                build(HttpStatus.INTERNAL_SERVER_ERROR)));
    }

    /**
     * Build common error message template.
     *
     * @param httpStatus the http status
     * @return the common response message
     */
    private ResponseMessage build(HttpStatus httpStatus) {
        return new ResponseMessageBuilder()
            .code(httpStatus.value())
            .message(HttpStatus.UNAUTHORIZED.getReasonPhrase())
            .responseModel(new ModelRef(ErrorDto.class.getSimpleName()))
            .build();
    }
}

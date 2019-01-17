package com.vgalloy.empire.webservice.controller;

import com.vgalloy.empire.webservice.ServerConfig;
import com.vgalloy.empire.webservice.dto.ErrorDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Created by Vincent Galloy on 08/05/18.
 *
 * @author Vincent Galloy
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(
    classes = ServerConfig.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ErrorTest {

  @Autowired private TestRestTemplate restTemplate;

  @Test
  void error404() {
    // GIVEN
    final String nonExistingUrl = "/azeaz";

    // WHEN
    final ResponseEntity<ErrorDto> result =
        restTemplate.getForEntity(nonExistingUrl, ErrorDto.class);

    // THEN
    Assertions.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    Assertions.assertNotNull(result.getBody());
    Assertions.assertEquals(HttpStatus.NOT_FOUND.value(), result.getBody().getCode());
    Assertions.assertEquals(HttpStatus.NOT_FOUND.getReasonPhrase(), result.getBody().getMessage());
  }
}

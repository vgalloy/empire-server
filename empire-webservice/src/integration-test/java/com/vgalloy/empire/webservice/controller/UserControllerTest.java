package com.vgalloy.empire.webservice.controller;

import com.vgalloy.empire.webservice.ServerConfig;
import com.vgalloy.empire.webservice.dto.UserDto;
import com.vgalloy.empire.webservice.resource.ResourceList;
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
 * Created by Vincent Galloy on 21/10/18.
 *
 * @author Vincent Galloy
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(
    classes = ServerConfig.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

  @Autowired private TestRestTemplate restTemplate;

  @Test
  void getAllMustBeEmpty() {
    // GIVEN
    final String nonExistingUrl = "/users";

    // WHEN
    @SuppressWarnings("unchecked")
    final ResponseEntity<ResourceList<UserDto>> result =
        (ResponseEntity<ResourceList<UserDto>>)
            (ResponseEntity) restTemplate.getForEntity(nonExistingUrl, ResourceList.class);

    // THEN
    Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    Assertions.assertNotNull(result.getBody());
    Assertions.assertTrue(result.getBody().getResult().isEmpty());
  }
}

package com.vgalloy.empire.webservice.controller;

import com.vgalloy.empire.webservice.ServerConfig;
import com.vgalloy.empire.webservice.dto.UserDto;
import com.vgalloy.empire.webservice.resource.ResourceList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Vincent Galloy on 21/10/18.
 *
 * @author Vincent Galloy
 */
@RunWith(SpringRunner.class)
@Import(ServerConfig.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

  @Autowired private TestRestTemplate restTemplate;

  @Test
  public void getAllMustBeEmpty() {
    // GIVEN
    final String nonExistingUrl = "/users";

    // WHEN
    @SuppressWarnings("unchecked")
    final ResponseEntity<ResourceList<UserDto>> result =
        (ResponseEntity<ResourceList<UserDto>>)
            (ResponseEntity) restTemplate.getForEntity(nonExistingUrl, ResourceList.class);

    // THEN
    Assert.assertEquals(HttpStatus.OK, result.getStatusCode());
    Assert.assertNotNull(result.getBody());
    Assert.assertTrue(result.getBody().getResult().isEmpty());
  }
}

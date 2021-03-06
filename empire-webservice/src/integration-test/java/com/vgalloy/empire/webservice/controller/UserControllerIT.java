package com.vgalloy.empire.webservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vgalloy.empire.webservice.ServerConfig;
import com.vgalloy.empire.webservice.dto.UserDto;
import com.vgalloy.empire.webservice.resource.ResourceList;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by Vincent Galloy on 21/10/18.
 *
 * @author Vincent Galloy
 */
@SpringBootTest(
    classes = ServerConfig.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

  @LocalServerPort private int port;
  @Autowired private TestRestTemplate restTemplate;

  @Test
  void getAllMustBeEmpty() {
    // GIVEN
    final var url = "/users";

    // WHEN
    @SuppressWarnings("unchecked")
    final ResponseEntity<ResourceList<UserDto>> result =
        (ResponseEntity<ResourceList<UserDto>>)
            (ResponseEntity<?>) restTemplate.getForEntity(url, ResourceList.class);

    // THEN
    Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    Assertions.assertNotNull(result.getBody());
    Assertions.assertTrue(result.getBody().getResult().isEmpty());
  }

  @Test
  void createUser() throws Exception {
    // GIVEN
    final HttpEntity<UserDto> httpEntity = userRequest();

    // WHEN
    final ResponseEntity<String> result =
        restTemplate.exchange(
            "/users/a91b1d64-aff6-4587-ac5c-6a58822b5b75",
            HttpMethod.PUT,
            httpEntity,
            String.class);

    // THEN
    final ObjectMapper mapper = new ObjectMapper();
    final String body = result.getBody();
    final String expected =
        getResource("output.json")
            .replaceAll("PORT", "" + port)
            .replaceAll("EXECUTION_TIME", "" + extractExecutionTime(body));
    Assertions.assertEquals(mapper.readTree(expected), mapper.readTree(body));
  }

  private HttpEntity<UserDto> userRequest() {
    final UserDto user = new UserDto();
    user.setLogin("login");
    user.setPassword("password");
    return new HttpEntity<>(user);
  }

  private static int extractExecutionTime(final String body) {
    final Matcher m = Pattern.compile("[0-9]{1,3}").matcher(body);
    if (m.find()) {
      return Integer.parseInt(m.group());
    }
    throw new IllegalStateException("No match");
  }

  private static String getResource(final String resourceName) {
    try {
      final URI uri = UserControllerTest.class.getClassLoader().getResource(resourceName).toURI();
      return new String(Files.readAllBytes(Paths.get(uri)));
    } catch (IOException | URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }
}

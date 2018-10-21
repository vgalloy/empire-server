package com.vgalloy.empire.webservice.controller;

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

import com.vgalloy.empire.webservice.ServerConfig;
import com.vgalloy.empire.webservice.dto.ErrorDto;

/**
 * Created by Vincent Galloy on 08/05/18.
 *
 * @author Vincent Galloy
 */
@RunWith(SpringRunner.class)
@Import(ServerConfig.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ErrorTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void error404() {
        // GIVEN
        final String nonExistingUrl = "/azeaz";

        // WHEN
        final ResponseEntity<ErrorDto> result = restTemplate.getForEntity(nonExistingUrl, ErrorDto.class);

        // THEN
        Assert.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        Assert.assertNotNull(result.getBody());
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), result.getBody().getCode());
        Assert.assertEquals(HttpStatus.NOT_FOUND.getReasonPhrase(), result.getBody().getMessage());
    }
}

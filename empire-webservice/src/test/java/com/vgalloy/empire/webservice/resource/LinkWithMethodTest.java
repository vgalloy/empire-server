package com.vgalloy.empire.webservice.resource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Vincent Galloy on 19/10/18.
 *
 * @author Vincent Galloy
 */
public class LinkWithMethodTest {

    public static class Sample {
        @RequestMapping("test")
        Sample test() {
            return new Sample();
        }
    }

    @Test
    public void objectWithNoRequestMapping() {
        // GIVEN
        final Sample proxy = ControllerLinkBuilder.methodOn(Sample.class).test();

        // WHEN
        final LinkWithMethod result = LinkWithMethod.linkTo(proxy);

        // THEN
        Assert.assertNotNull(result.getMethods());
        Assert.assertEquals(0, result.getMethods().length);
    }

    @Test
    public void selfMaintainCorrectType() {
        // GIVEN
        final Sample proxy = ControllerLinkBuilder.methodOn(Sample.class).test();

        // WHEN
        final Link result = LinkWithMethod.linkTo(proxy).withSelfRel();

        // THEN
        Assert.assertTrue(result instanceof LinkWithMethod);
    }
}
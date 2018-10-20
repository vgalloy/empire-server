package com.vgalloy.empire.webservice.resource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Vincent Galloy on 19/10/18.
 *
 * @author Vincent Galloy
 */
public class LinkWithMethodTest {

    public interface Sample {

        @PostMapping("post")
        Sample test3();
    }

    public static class SampleImpl implements Sample {

        @RequestMapping("test")
        Sample test() {
            return new SampleImpl();
        }

        @PutMapping("OK")
        Sample test2() {
            return new SampleImpl();
        }

        @Override
        public Sample test3() {
            return new SampleImpl();
        }
    }

    @Test
    public void objectWithNoRequestMapping() {
        // GIVEN
        final Sample proxy = ControllerLinkBuilder.methodOn(SampleImpl.class).test();

        // WHEN
        final LinkWithMethod result = LinkWithMethod.linkTo(proxy);

        // THEN
        Assert.assertNotNull(result.getMethods());
        Assert.assertEquals(RequestMethod.values().length, result.getMethods().length);
    }

    @Test
    public void selfMaintainCorrectType() {
        // GIVEN
        final Sample proxy = ControllerLinkBuilder.methodOn(SampleImpl.class).test();

        // WHEN
        final Link result = LinkWithMethod.linkTo(proxy).withSelfRel();

        // THEN
        Assert.assertEquals(LinkWithMethod.class, result.getClass());
    }

    @Test
    public void changeHref() {
        // GIVEN
        final Sample proxy = ControllerLinkBuilder.methodOn(SampleImpl.class).test();

        // WHEN
        final Link result = LinkWithMethod.linkTo(proxy).withHref("TOTO");

        // THEN
        Assert.assertEquals(LinkWithMethod.class, result.getClass());
        Assert.assertEquals("TOTO", result.getHref());
    }

    @Test
    public void aliasFor() {
        // GIVEN
        final Sample proxy = ControllerLinkBuilder.methodOn(SampleImpl.class).test2();

        // WHEN
        final LinkWithMethod result = LinkWithMethod.linkTo(proxy);

        // THEN
        Assert.assertNotNull(result.getMethods());
        Assert.assertEquals(1, result.getMethods().length);
        Assert.assertEquals(RequestMethod.PUT, result.getMethods()[0]);
    }

    @Test
    public void findInterface() {
        // GIVEN
        final Sample proxy = ControllerLinkBuilder.methodOn(Sample.class).test3();

        // WHEN
        final LinkWithMethod result = LinkWithMethod.linkTo(proxy);

        // THEN
        Assert.assertNotNull(result.getMethods());
        Assert.assertEquals(1, result.getMethods().length);
        Assert.assertEquals(RequestMethod.POST, result.getMethods()[0]);
    }
}
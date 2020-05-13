package com.vgalloy.empire.webservice.resource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("test")
    Sample test() {
      return new SampleImpl();
    }

    @RequestMapping("all")
    Sample all() {
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
  void objectWithNoRequestMapping() {
    // GIVEN
    final Sample proxy = WebMvcLinkBuilder.methodOn(SampleImpl.class).all();

    // WHEN
    final LinkWithMethod[] result = LinkWithMethod.linkTo(proxy);

    // THEN
    Assertions.assertNotNull(result);
    Assertions.assertEquals(RequestMethod.values().length, result.length);
  }

  @Test
  void selfMaintainCorrectType() {
    // GIVEN
    final Sample proxy = WebMvcLinkBuilder.methodOn(SampleImpl.class).test();

    // WHEN
    final Link result = LinkWithMethod.self(proxy).withSelfRel();

    // THEN
    Assertions.assertEquals(LinkWithMethod.class, result.getClass());
  }

  @Test
  void changeHref() {
    // GIVEN
    final Sample proxy = WebMvcLinkBuilder.methodOn(SampleImpl.class).test();

    // WHEN
    final Link result = LinkWithMethod.self(proxy).withHref("TOTO");

    // THEN
    Assertions.assertEquals(LinkWithMethod.class, result.getClass());
    Assertions.assertEquals("TOTO", result.getHref());
  }

  @Test
  void aliasFor() {
    // GIVEN
    final Sample proxy = WebMvcLinkBuilder.methodOn(SampleImpl.class).test2();

    // WHEN
    final LinkWithMethod[] result = LinkWithMethod.linkTo(proxy);

    // THEN
    Assertions.assertNotNull(result);
    Assertions.assertEquals(1, result.length);
    Assertions.assertEquals(RequestMethod.PUT, result[0].getMethod());
  }

  @Test
  void findInterface() {
    // GIVEN
    final Sample proxy = WebMvcLinkBuilder.methodOn(Sample.class).test3();

    // WHEN
    final LinkWithMethod[] result = LinkWithMethod.linkTo(proxy);

    // THEN
    Assertions.assertNotNull(result);
    Assertions.assertEquals(1, result.length);
    Assertions.assertEquals(RequestMethod.POST, result[0].getMethod());
  }
}

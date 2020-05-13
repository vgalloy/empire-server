package com.vgalloy.empire.common.nullable;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Created by Vincent Galloy on 01/05/18.
 *
 * @author Vincent Galloy
 */
@Import(NotNullApiAspectIT.Config.class)
@ExtendWith(SpringExtension.class)
final class NotNullApiAspectIT {

  @Autowired private SimpleClass simpleClass;

  @NotNullApi
  public static class SimpleClass {

    Integer add(final Integer a, final Integer b) {
      return 1;
    }
  }

  @Configuration
  @EnableAspectJAutoProxy
  static class Config {

    @Bean
    public SimpleClass simpleClass() {
      return new SimpleClass();
    }

    @Bean
    public NotNullApiAspect notNullApiAspect() {
      return new NotNullApiAspect();
    }
  }

  @Test
  void notNullParameter() {
    simpleClass.add(1, 2);

    // WHEN
    // no exception occurred
  }

  @Test
  void withNullParameter() {
    // WHEN
    final var exception =
        Assertions.assertThrows(NullPointerException.class, () -> simpleClass.add(null, 1));

    // THEN
    Assertions.assertEquals(
        "com.vgalloy.empire.common.nullable.NotNullApiAspectIT$SimpleClass#add is mark as @NotNullApi and received 'null' for parameter arg0",
        exception.getMessage());
  }
}

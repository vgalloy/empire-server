package com.vgalloy.empire.common.nullable;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Vincent Galloy on 01/05/18.
 *
 * @author Vincent Galloy
 */
@Import(NotNullApiAspectIT.Config.class)
@RunWith(SpringRunner.class)
public final class NotNullApiAspectIT {

  @Rule public ExpectedException expectedException = ExpectedException.none();

  @Autowired private SimpleClass simpleClass;

  @NotNullApi
  public static class SimpleClass {

    public Integer add(final Integer a, final Integer b) {
      return 1;
    }
  }

  @Configuration
  @EnableAspectJAutoProxy
  public static class Config {

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
  public void notNullParameter() {
    simpleClass.add(1, 2);

    // WHEN
    // no exception occurred
  }

  @Test
  public void withNullParameter() {
    expectedException.expect(NullPointerException.class);
    expectedException.expectMessage(
        "com.vgalloy.empire.common.nullable.NotNullApiAspectIT$SimpleClass#add is mark as @NotNullApi and received 'null' for parameter arg0");
    simpleClass.add(null, 1);
  }
}

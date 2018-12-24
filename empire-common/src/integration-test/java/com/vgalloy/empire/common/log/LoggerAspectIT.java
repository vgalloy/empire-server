package com.vgalloy.empire.common.log;

import com.vgalloy.empire.common.LogLevel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
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
@Import(LoggerAspectIT.Config.class)
@RunWith(SpringRunner.class)
public final class LoggerAspectIT {

  private static final Logger LOGGER = BDDMockito.mock(Logger.class);

  @Autowired private SimpleClass simpleClass;

  public static class SimpleClass {

    @FullLog
    public int add(final int a, final int b) {
      return a + b;
    }

    @FullLog(LogLevel.ERROR)
    public int minus(final int a, final int b) {
      return a - b;
    }
  }

  public static class CustomLoggerFactory implements ILoggerFactory {

    @Override
    public Logger getLogger(final String name) {
      return LOGGER;
    }
  }

  @EnableAspectJAutoProxy
  @Configuration
  public static class Config {

    @Bean
    public SimpleClass simpleClass() {
      return new SimpleClass();
    }

    @Bean
    public ILoggerFactory loggerFactory() {
      return new CustomLoggerFactory();
    }

    @Bean
    public LoggerAspect loggerAspect(final ILoggerFactory loggerFactory) {
      return new LoggerAspect(loggerFactory);
    }
  }

  @Test
  public void addWithTraceLevel() {
    // WHEN
    simpleClass.add(1, 2);

    // THEN
    BDDMockito.then(LOGGER).should(BDDMockito.times(1)).trace("[ START ] : add(1, 2)");
    BDDMockito.then(LOGGER).should(BDDMockito.times(1)).trace("[ END   ] : add ==> 3");
  }

  @Test
  public void minusWithErrorLevel() {
    // WHEN
    simpleClass.minus(1, 2);

    // THEN
    BDDMockito.then(LOGGER).should(BDDMockito.times(1)).error("[ START ] : minus(1, 2)");
    BDDMockito.then(LOGGER).should(BDDMockito.times(1)).error("[ END   ] : minus ==> -1");
  }
}

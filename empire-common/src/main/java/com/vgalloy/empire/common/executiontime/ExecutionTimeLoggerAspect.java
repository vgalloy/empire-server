package com.vgalloy.empire.common.executiontime;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by Vincent Galloy on 01/03/18. Manage {@link ExecutionTimeLog} annotation.
 *
 * @author Vincent Galloy
 */
@Aspect
@Component
public final class ExecutionTimeLoggerAspect {

  /**
   * Log execution time.
   *
   * @param joinPoint the jointPoint
   * @param methodLog the annotation
   * @return method result
   * @throws Throwable forward method throwable
   */
  @Around("@annotation(methodLog)")
  public Object logExecutionTime(
      final ProceedingJoinPoint joinPoint, final ExecutionTimeLog methodLog) throws Throwable {
    final var start = System.nanoTime();
    try {
      return joinPoint.proceed();
    } finally {
      final var totalTimeMillis = (System.nanoTime() - start) / 1_000_000;
      final var message =
          joinPoint.getTarget().getClass().getSimpleName()
              + "#"
              + joinPoint.getSignature().getName()
              + " : "
              + totalTimeMillis
              + " ms";
      final var logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
      final var logLevel = methodLog.value();
      logLevel.log(logger, message);
    }
  }
}

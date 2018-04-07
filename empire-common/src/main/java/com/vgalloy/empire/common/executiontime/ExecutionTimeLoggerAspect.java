package com.vgalloy.empire.common.executiontime;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.vgalloy.empire.common.LogLevel;

/**
 * Created by Vincent Galloy on 01/03/18.
 * Manage {@link ExecutionTimeLog} annotation.
 *
 * @author Vincent Galloy
 */
@Aspect
@Component
public class ExecutionTimeLoggerAspect {

    /**
     * Log execution time.
     *
     * @param joinPoint the jointPoint
     * @param methodLog the annotation
     * @return method result
     * @throws Throwable forward method throwable
     */
    @Around("@annotation(methodLog)")
    public final Object logExecutionTime(ProceedingJoinPoint joinPoint, ExecutionTimeLog methodLog) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
        } finally {
            long totalTimeMillis = System.currentTimeMillis() - start;
            String message = joinPoint.getTarget().getClass().getSimpleName() + "#" + joinPoint.getSignature().getName() + " : " + totalTimeMillis + " ms";
            Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());
            LogLevel logLevel = methodLog.value();
            LogLevel.printLog(logger, logLevel, message);
        }
    }
}

package com.vgalloy.empire.common.log;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import com.vgalloy.empire.common.LogLevel;

/**
 * Created by Vincent Galloy on 01/03/18.
 * <p>
 * The goal is to logs all information given to the method annotate with {@link FullLog} and the result.
 * <p>
 * If the class AND the method are annotated with {@link FullLog}, only the method annotation must be applied.
 *
 * @author Vincent Galloy
 */
@Aspect
@Component
public final class LoggerAspect {

    private final ILoggerFactory loggerFactory;

    /**
     * Constructor.
     *
     * @param loggerFactory the logger factory. Will be use for creating logger related to the wrapped class at runtime
     */
    public LoggerAspect(final ILoggerFactory loggerFactory) {
        this.loggerFactory = Objects.requireNonNull(loggerFactory);
    }

    /**
     * 1. Analyze pointcut to find arguments and display it
     * 2. Execute method
     * 3. Print the result and return it
     *
     * @param joinPoint the pointcut
     * @param logLevel  the expected log level
     * @return method result
     * @throws Throwable forward method throwable
     */
    private Object displayLog(final ProceedingJoinPoint joinPoint, final LogLevel logLevel) throws Throwable {
        final Logger logger = loggerFactory.getLogger(joinPoint.getTarget().getClass().getName());
        final String args = Stream.of(joinPoint.getArgs())
            .map(Object::toString)
            .collect(Collectors.joining(", "));
        StringBuilder stringBuilder = new StringBuilder("[ START ] : ")
            .append(joinPoint.getSignature().getName())
            .append('(')
            .append(args)
            .append(')');
        LogLevel.printLog(logger, logLevel, stringBuilder.toString());

        final Object result = joinPoint.proceed();

        stringBuilder = new StringBuilder("[ END   ] : ")
            .append(joinPoint.getSignature().getName())
            .append(" ==> ")
            .append(result);
        LogLevel.printLog(logger, logLevel, stringBuilder.toString());
        return result;
    }

    /**
     * Pointcut creation.
     * 1. @within is used to detect class annotated with {@link FullLog}
     * 2. @annotation is used to detect method annotated with {@link FullLog}
     * <p/>
     * The main issues here to handle double annotation : one on class and one on method. If both are present only
     * method must be applied
     * If pointcut define an OR ( || ) annotation parameter is field with the second part of the OR even if this one is
     * empty.
     * <p>
     * For example  : @within(methodLog) || @annotation(methodLog)
     * if class is annotated but the method is not, pointcut will be call but parameter "methodLog" will be empty.
     *
     * @param joinPoint the jointPoint representing wrapped method
     * @param methodLog the annotation related to the method, can be null
     * @param classLog  the annotation related to the class, can be null
     * @return the wrapped method result
     * @throws Throwable forward method possible throwable
     */
    @Around(value = "(@within(methodLog) || @annotation(methodLog)) && (@annotation(classLog) || @within(classLog))", argNames = "joinPoint, methodLog, classLog")
    public Object logForClass(final ProceedingJoinPoint joinPoint, final FullLog methodLog, final FullLog classLog) throws Throwable {
        if (methodLog != null) {
            return displayLog(joinPoint, methodLog.value());
        }
        return displayLog(joinPoint, classLog.value());
    }
}

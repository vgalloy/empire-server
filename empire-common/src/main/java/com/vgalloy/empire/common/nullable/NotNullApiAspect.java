package com.vgalloy.empire.common.nullable;

import java.lang.reflect.Method;
import java.util.Objects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * Created by Vincent Galloy on 01/03/18.
 * Manage {@link NotNullApi} annotation.
 *
 * @author Vincent Galloy
 */
@Aspect
@Component
public class NotNullApiAspect {

    /**
     * Assert all params of the method are not null.
     *
     * @param joinPoint the jointPoint
     * @param methodLog the annotation
     */
    @Before("@within(methodLog)")
    public final void logExecutionTime(JoinPoint joinPoint, NotNullApi methodLog) {
        Object[] args = joinPoint.getArgs();
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        for (int i = 0; i < args.length; i++) {
            Object arg = args[i];
            if (Objects.isNull(arg)) {
                String fullName = joinPoint.getSignature().getDeclaringType().getName() + "#" + joinPoint.getSignature().getName();
                String paramName = method.getParameters()[i].getName();
                String message = fullName + " is mark as @NotNullApi and received 'null' for parameter " + paramName;
                throw new NullPointerException(message);
            }
        }
    }
}

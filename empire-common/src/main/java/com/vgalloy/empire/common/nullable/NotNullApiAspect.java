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
public final class NotNullApiAspect {

    /**
     * Assert all params of the method are not null.
     *
     * @param joinPoint the jointPoint
     * @param methodLog the annotation
     */
    @Before("@within(methodLog)")
    public void logExecutionTime(final JoinPoint joinPoint, final NotNullApi methodLog) {
        final Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            final Object arg = args[i];
            final int index = i;
            Objects.requireNonNull(arg, () -> getErrorMessage(joinPoint, index));
        }
    }

    /**
     * Build the error message.
     *
     * @param joinPoint the join point
     * @param index     the index of the null argument
     * @return the error message
     */
    private String getErrorMessage(final JoinPoint joinPoint, final int index) {
        final String fullName = joinPoint.getSignature().getDeclaringType().getName() + "#" + joinPoint.getSignature().getName();
        final Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        final String paramName = method.getParameters()[index].getName();
        return fullName + " is mark as @NotNullApi and received 'null' for parameter " + paramName;
    }
}

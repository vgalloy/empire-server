package com.vgalloy.empire.feature.internal.common.aspect;

import java.util.Objects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.vgalloy.empire.feature.api.FeatureManager;
import com.vgalloy.empire.feature.api.FeatureMethod;

/**
 * Created by Vincent Galloy on 11/10/18.
 *
 * @author Vincent Galloy
 */
@Aspect
@Component
public class FeatureAspect {

    private final FeatureManager featureManager;

    /**
     * Constructor.
     *
     * @param featureManager the store, not null
     */
    public FeatureAspect(final FeatureManager featureManager) {
        this.featureManager = Objects.requireNonNull(featureManager);
    }

    /**
     * Execute the method if the feature is active.
     *
     * @param joinPoint     the jointPoint
     * @param featureMethod the feature Name
     * @return method result
     * @throws Throwable forward method throwable
     */
    @Around("@annotation(featureMethod)")
    public Object logExecutionTime(final ProceedingJoinPoint joinPoint, final FeatureMethod featureMethod) throws Throwable {
        final var name = featureMethod.value();
        final var featureConfiguration = this.featureManager.getById(name);
        if (featureConfiguration.isPresent() && featureConfiguration.get().isEnable()) {
            return joinPoint.proceed();
        }
        return null;
    }
}

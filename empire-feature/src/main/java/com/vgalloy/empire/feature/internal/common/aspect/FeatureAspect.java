package com.vgalloy.empire.feature.internal.common.aspect;

import java.util.Objects;
import java.util.Optional;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.vgalloy.empire.feature.api.FeatureMethod;
import com.vgalloy.empire.feature.internal.common.FeatureConfiguration;
import com.vgalloy.empire.feature.internal.common.store.FeatureConfigurationStore;

/**
 * Created by Vincent Galloy on 11/10/18.
 *
 * @author Vincent Galloy
 */
@Aspect
@Component
public class FeatureAspect {

    private final FeatureConfigurationStore featureConfigurationStore;

    /**
     * Constructor.
     *
     * @param featureConfigurationStore the store, not null
     */
    public FeatureAspect(final FeatureConfigurationStore featureConfigurationStore) {
        this.featureConfigurationStore = Objects.requireNonNull(featureConfigurationStore);
    }

    /**
     * Execute the method if the feature is active.
     *
     * @param joinPoint   the jointPoint
     * @param featureMethod the feature Name
     * @return method result
     * @throws Throwable forward method throwable
     */
    @Around("@annotation(featureMethod)")
    public Object logExecutionTime(final ProceedingJoinPoint joinPoint, final FeatureMethod featureMethod) throws Throwable {
        final String name = featureMethod.value();
        final Optional<FeatureConfiguration> featureConfiguration = this.featureConfigurationStore.getById(name);
        if (featureConfiguration.isPresent() && featureConfiguration.get().isEnable()) {
            return joinPoint.proceed();
        }
        return null;
    }
}

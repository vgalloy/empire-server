package com.vgalloy.empire.feature.internal.common.utils;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by Vincent Galloy on 22/10/18.
 *
 * @author Vincent Galloy
 */
public final class FeatureUtils {

    /**
     * Constructor.
     * Private to avoid instantiation
     */
    private FeatureUtils() {
        throw new AssertionError();
    }

    /**
     * Utility function to convert a simple consumer into a mapper.
     *
     * @param consumer the consumer
     * @param <T>      the generic type of the object
     * @return the map function
     */
    public static <T> Function<T, T> peekToMap(final Consumer<T> consumer) {
        return object -> {
            consumer.accept(object);
            return object;
        };
    }
}

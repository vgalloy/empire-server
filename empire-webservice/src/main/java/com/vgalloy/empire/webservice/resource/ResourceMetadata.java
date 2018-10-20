package com.vgalloy.empire.webservice.resource;

import java.time.Duration;

import org.springframework.lang.Nullable;

import com.vgalloy.empire.webservice.filter.TimerContextHolder;

/**
 * Created by Vincent Galloy on 19/10/18.
 *
 * @author Vincent Galloy
 */
public class ResourceMetadata {

    @Nullable
    private final Long executionTime;

    /**
     * Constructor.
     */
    public ResourceMetadata() {
        this(null);
    }

    /**
     * Constructor.
     *
     * @param executionTime the request execution time
     */
    public ResourceMetadata(final Long executionTime) {
        this.executionTime = executionTime;
    }

    @Nullable
    public Long getExecutionTime() {
        return executionTime;
    }

    /**
     * Build the metadata with the current timer.
     *
     * @return a new meta data
     */
    public static ResourceMetadata fromNow() {
        return TimerContextHolder.getTimerDuration()
            .map(Duration::toMillis)
            .map(ResourceMetadata::new)
            .orElseGet(ResourceMetadata::new);
    }
}

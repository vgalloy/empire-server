package com.vgalloy.empire.webservice.resource;

import org.springframework.lang.Nullable;

import com.vgalloy.empire.webservice.filter.TimerContextHolder;

/**
 * Created by Vincent Galloy on 19/10/18.
 *
 * @author Vincent Galloy
 */
public class ResourceMetaData {

    @Nullable
    private final Long executionTime;

    /**
     * Constructor.
     */
    public ResourceMetaData() {
        this(null);
    }

    /**
     * Constructor.
     *
     * @param executionTime the request execution time
     */
    public ResourceMetaData(final Long executionTime) {
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
    public static ResourceMetaData fromNow() {
        return TimerContextHolder.getExecutionTimeMillis()
            .map(ResourceMetaData::new)
            .orElseGet(ResourceMetaData::new);
    }
}

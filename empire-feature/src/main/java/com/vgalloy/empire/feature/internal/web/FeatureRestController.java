package com.vgalloy.empire.feature.internal.web;

import java.util.Collection;
import java.util.Objects;

import javax.servlet.http.HttpServlet;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vgalloy.empire.feature.api.FeatureManager;
import com.vgalloy.empire.feature.api.exception.WebNotFoundException;
import com.vgalloy.empire.feature.internal.common.FeatureConfiguration;

/**
 * Created by Vincent Galloy on 11/10/18.
 *
 * @author Vincent Galloy
 */
@ConditionalOnClass(HttpServlet.class)
@RequestMapping("features")
@RestController
public class FeatureRestController {

    private final FeatureManager featureManager;

    /**
     * Constructor.
     *
     * @param featureManager feature store, not null
     */
    public FeatureRestController(final FeatureManager featureManager) {
        this.featureManager = Objects.requireNonNull(featureManager);
    }

    /**
     * Get all feature.
     *
     * @return all available feature
     */
    @GetMapping
    public Collection<FeatureConfiguration> getAll() {
        return featureManager.getAll();
    }

    /**
     * Get one feature by its id.
     *
     * @param featureId feature id, not null
     * @return the feature
     */
    @GetMapping("{featureId}")
    public FeatureConfiguration getById(final @PathVariable String featureId) {
        return featureManager.getById(featureId).orElseThrow(() -> new WebNotFoundException(featureId));
    }

    /**
     * Update one feature.
     *
     * @param featureId            the feature id, not null
     * @param featureConfiguration the new configuration, not null
     * @return the updated configuration
     */
    @PutMapping("{featureId}")
    public FeatureConfiguration update(final @PathVariable String featureId, final @RequestBody FeatureConfiguration featureConfiguration) {
        // TODO
        final FeatureConfiguration newfeature = new FeatureConfiguration(featureId, featureConfiguration.isEnable());
        return featureManager.update(newfeature);
    }
}

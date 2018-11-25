package com.vgalloy.empire.i18n;

import java.util.Objects;

/**
 * Created by Vincent Galloy on 07/04/18.
 *
 * @author Vincent Galloy
 */
public class Translation {

    private final TranslationKey key;
    private final String value;

    /**
     * Constructor.
     *
     * @param key   the key
     * @param value the value
     */
    public Translation(final TranslationKey key, final String value) {
        this.key = Objects.requireNonNull(key, "key");
        this.value = Objects.requireNonNull(value, "value");
    }

    /**
     * The code.
     *
     * @return the code, not null
     */
    public final TranslationKey getKey() {
        return key;
    }

    /**
     * The result of the value.
     *
     * @return the value
     */
    public final String getValue() {
        return value;
    }
}

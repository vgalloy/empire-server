package com.vgalloy.empire.i18n.internal;

import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

import com.vgalloy.empire.i18n.translation.Translation;

/**
 * Created by Vincent Galloy on 07/04/18.
 *
 * @author Vincent Galloy
 */
final class EmptyTranslationResult implements Translation {

    private final String code;

    /**
     * Constructor.
     *
     * @param code the code
     */
    EmptyTranslationResult(String code) {
        this.code = Objects.requireNonNull(code);
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public Optional<Locale> getLocale() {
        return Optional.empty();
    }

    @Override
    public String getTranslation() {
        return code;
    }
}

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
final class CorrectTranslation implements Translation {

    private final String code;
    private final Locale locale;
    private final String translation;

    /**
     * Constructor.
     *
     * @param code        the code
     * @param locale      the locale
     * @param translation the translation
     */
    CorrectTranslation(final String code, final Locale locale, final String translation) {
        this.code = Objects.requireNonNull(code);
        this.locale = Objects.requireNonNull(locale);
        this.translation = Objects.requireNonNull(translation);
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public Optional<Locale> getLocale() {
        return Optional.of(locale);
    }

    @Override
    public String getTranslation() {
        return translation;
    }
}

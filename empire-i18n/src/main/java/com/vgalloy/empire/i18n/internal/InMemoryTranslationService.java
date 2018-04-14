package com.vgalloy.empire.i18n.internal;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.vgalloy.empire.i18n.TranslationService;
import com.vgalloy.empire.i18n.translation.Translation;

/**
 * Created by Vincent Galloy on 07/04/18.
 *
 * @author Vincent Galloy
 */
@Service
public final class InMemoryTranslationService implements TranslationService {

    private final Map<String, Map<Locale, String>> translationTable = new HashMap<>();

    @Override
    public Translation translate(final String code, final Locale locale) {
        Objects.requireNonNull(code, "code");
        Objects.requireNonNull(locale, "locale");

        final Map<Locale, String> byLocaleTable = translationTable.computeIfAbsent(code, c -> new HashMap<>());
        final String translation = byLocaleTable.get(locale);

        if (Objects.isNull(translation)) {
            return new EmptyTranslationResult(code);
        } else {
            return new CorrectTranslation(code, locale, translation);
        }
    }

    @Override
    public void addCode(final String code, final Locale locale, final String translation) {
        Objects.requireNonNull(code, "code");
        Objects.requireNonNull(locale, "locale");
        Objects.requireNonNull(translation, "translation");

        final Map<Locale, String> byLocaleTable = translationTable.computeIfAbsent(code, c -> new HashMap<>());
        byLocaleTable.put(locale, translation);
    }
}

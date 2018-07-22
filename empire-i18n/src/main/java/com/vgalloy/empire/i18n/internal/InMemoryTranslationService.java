package com.vgalloy.empire.i18n.internal;

import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.vgalloy.empire.i18n.TranslationService;
import com.vgalloy.empire.i18n.translation.Translation;

/**
 * Created by Vincent Galloy on 07/04/18.
 *
 * @author Vincent Galloy
 */
@Service
class InMemoryTranslationService implements TranslationService {

    /***
     * The first String represents the language.
     * The second String represents the variant (country)
     * The third String represents the code
     * The value represents the translation
     */
    private final Map<String, Map<String, Map<String, String>>> translationTable = new ConcurrentHashMap<>();

    @Override
    public Translation translate(final String code, final Locale locale) {
        Objects.requireNonNull(code, "code");
        Objects.requireNonNull(locale, "locale");

        final Map<String, Map<String, String>> byLanguageTranslation = translationTable.computeIfAbsent(locale.getLanguage(), c -> new ConcurrentHashMap<>());
        final Map<String, String> byLanguageAndCountryTranslation = byLanguageTranslation.computeIfAbsent(locale.getCountry(), country -> new ConcurrentHashMap<>());
        final String translation = byLanguageAndCountryTranslation.get(code);

        if (Objects.nonNull(translation)) {
            return new CorrectTranslation(code, locale, translation);
        }
        final Map<String, String> byLanguageAndNoCountryTranslation = byLanguageTranslation.computeIfAbsent("", country -> new ConcurrentHashMap<>());
        final String noCountryTranslation = byLanguageAndNoCountryTranslation.get(code);
        if (Objects.isNull(noCountryTranslation)) {
            return new EmptyTranslationResult(code);
        } else {
            return new CorrectTranslation(code, new Locale(locale.getLanguage(), ""), noCountryTranslation);
        }
    }

    @Override
    public void addCode(final String code, final Locale locale, final String translation) {
        Objects.requireNonNull(code, "code");
        Objects.requireNonNull(locale, "locale");
        Objects.requireNonNull(translation, "translation");

        final Map<String, Map<String, String>> byLanguageTranslation = translationTable.computeIfAbsent(locale.getLanguage(), c -> new ConcurrentHashMap<>());
        final Map<String, String> byLanguageAndCountryTranslation = byLanguageTranslation.computeIfAbsent(locale.getCountry(), country -> new ConcurrentHashMap<>());
        byLanguageAndCountryTranslation.put(code, translation);
        final Map<String, String> byLanguageAndNoCountryTranslation = byLanguageTranslation.computeIfAbsent("", country -> new ConcurrentHashMap<>());
        byLanguageAndNoCountryTranslation.put(code, translation);
    }
}

package com.vgalloy.empire.i18n;

import java.util.Locale;

import com.vgalloy.empire.i18n.translation.Translation;

/**
 * Created by Vincent Galloy on 07/04/18.
 *
 * @author Vincent Galloy
 */
public interface TranslationService {

    /**
     * Translate the given translationCode.
     *
     * @param code   the code to translate, not null
     * @param locale the locale
     * @return the translation
     */
    Translation translate(String code, Locale locale);

    /**
     * Add the code.
     *
     * @param code        the code
     * @param locale      the local
     * @param translation the translation
     */
    void addCode(String code, Locale locale, String translation);
}

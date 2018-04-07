package com.vgalloy.empire.i18n.translation;

import java.util.Locale;
import java.util.Optional;

/**
 * Created by Vincent Galloy on 07/04/18.
 *
 * @author Vincent Galloy
 */
public interface Translation {

    /**
     * The code.
     *
     * @return the code, not null
     */
    String getCode();

    /**
     * The locale of translation.
     *
     * @return the requested locale for translation, not null
     */
    Optional<Locale> getLocale();

    /**
     * The result of the translation.
     *
     * @return the translation
     */
    String getTranslation();
}

package com.vgalloy.empire.i18n;

/**
 * Created by Vincent Galloy on 07/04/18.
 *
 * @author Vincent Galloy
 */
public interface TranslationStore {

    /**
     * Translate the given translationCode.
     *
     * @param translationKey the translationKey, not null
     * @return the value
     */
    Translation translate(TranslationKey translationKey);

    /**
     * Add a value to the store.
     *
     * @param translation the value
     */
    void createOrUpdateExact(Translation translation);

    /**
     * Add a value to the store.
     *
     * @param translation the value
     */
    void createOrUpdateRecursivelyIfMissing(Translation translation);

    /**
     * Add a value to the store.
     *
     * @param translation the value
     */
    void createOrUpdateRecursivelyForce(Translation translation);
}

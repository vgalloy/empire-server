package com.vgalloy.empire.i18n;

import java.util.List;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Vincent Galloy on 17/11/18.
 *
 * @author Vincent Galloy
 */
public final class TranslationKeyTest {

    @Test
    public void notDefault() {
        // GIVEN
        final TranslationKey translationKey = new TranslationKey("CODE", new Locale("fr"));

        // WHEN
        final boolean result = translationKey.isDefault();

        // THEN
        Assert.assertFalse(result);
    }

    @Test
    public void isDefault() {
        // GIVEN
        final TranslationKey translationKey = new TranslationKey("CODE", new Locale(""));

        // WHEN
        final boolean result = translationKey.isDefault();

        // THEN
        Assert.assertTrue(result);
    }

    @Test
    public void parent() {
        // GIVEN
        final TranslationKey translationKey = new TranslationKey("CODE", Locale.CANADA_FRENCH);

        // WHEN
        final TranslationKey parent = translationKey.getParent();

        // THEN
        Assert.assertFalse(parent.isDefault());
        Assert.assertEquals(Locale.FRENCH, parent.locale());
        Assert.assertEquals("CODE", parent.code());
    }

    @Test
    public void root() {
        // GIVEN
        final TranslationKey translationKey = new TranslationKey("CODE", Locale.CANADA_FRENCH);

        // WHEN
        final TranslationKey root = translationKey.getParent()
            .getParent();

        // THEN
        Assert.assertTrue(root.isDefault());
        Assert.assertEquals(new Locale(""), root.locale());
        Assert.assertEquals(root, root.getParent());
    }

    @Test
    public void hierarchy() {
        // GIVEN
        final TranslationKey translationKey = new TranslationKey("CODE", Locale.CANADA_FRENCH);

        // WHEN
        final List<TranslationKey> hierarchy = translationKey.getHierarchy();

        // THEN
        Assert.assertEquals(3, hierarchy.size());
        Assert.assertEquals(translationKey, hierarchy.get(0));
        Assert.assertTrue(hierarchy.get(2).isDefault());

        Assert.assertEquals("fr-CA", hierarchy.get(0).locale().toLanguageTag());
        Assert.assertEquals("fr", hierarchy.get(1).locale().toLanguageTag());
        Assert.assertEquals("und", hierarchy.get(2).locale().toLanguageTag());
    }

    @Test
    public void getParentWithScriptAndCountry() {
        // GIVEN
        final var locale = Locale.forLanguageTag("fr-Cyrl-FR");
        final TranslationKey translationKey = new TranslationKey("CODE", locale);

        // WHEN
        final TranslationKey result = translationKey.getParent();

        // THEN
        Assert.assertEquals("fr-Cyrl", result.locale().toLanguageTag());
    }

    @Test
    public void getParentWithScript() {
        // GIVEN
        final var locale = Locale.forLanguageTag("fr-Cyrl");
        final TranslationKey translationKey = new TranslationKey("CODE", locale);

        // WHEN
        final TranslationKey result = translationKey.getParent();

        // THEN
        Assert.assertEquals("fr", result.locale().toLanguageTag());
    }

    @Test
    public void hierarchyWithScript() {
        // GIVEN
        final var locale = Locale.forLanguageTag("fr-Cyrl-FR");
        final TranslationKey translationKey = new TranslationKey("CODE", locale);

        // WHEN
        final List<TranslationKey> hierarchy = translationKey.getHierarchy();

        // THEN
        Assert.assertEquals(4, hierarchy.size());
        Assert.assertEquals(translationKey, hierarchy.get(0));
        Assert.assertTrue(hierarchy.get(3).isDefault());

        Assert.assertEquals("fr-Cyrl-FR", hierarchy.get(0).locale().toLanguageTag());
        Assert.assertEquals("fr-Cyrl", hierarchy.get(1).locale().toLanguageTag());
        Assert.assertEquals("fr", hierarchy.get(2).locale().toLanguageTag());
        Assert.assertEquals("und", hierarchy.get(3).locale().toLanguageTag());
    }

    @Test
    public void comparable() {
        // GIVEN
        final TranslationKey frFr = new TranslationKey("CODE", Locale.FRANCE);
        final TranslationKey frCA = new TranslationKey("CODE", Locale.CANADA_FRENCH);

        // WHEN
        final int result = frFr.compareTo(frCA);

        // THEN
        Assert.assertTrue(0 < result);
    }
}
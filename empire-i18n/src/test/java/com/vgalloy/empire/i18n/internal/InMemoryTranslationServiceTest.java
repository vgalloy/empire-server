package com.vgalloy.empire.i18n.internal;

import java.util.Locale;

import org.junit.Assert;
import org.junit.Test;

import com.vgalloy.empire.i18n.TranslationService;
import com.vgalloy.empire.i18n.translation.Translation;

/**
 * Created by Vincent Galloy on 07/04/18.
 *
 * @author Vincent Galloy
 */
public final class InMemoryTranslationServiceTest {

    @Test
    public void noTranslation() {
        // GIVEN
        TranslationService translationService = new InMemoryTranslationService();

        // WHEN
        Translation translation = translationService.translate("CODE", Locale.CANADA);

        // THEN
        Assert.assertFalse(translation.getLocale().isPresent());
        Assert.assertEquals("CODE", translation.getCode());
        Assert.assertEquals("CODE", translation.getTranslation());
    }

    @Test
    public void oneTranslation() {
        // GIVEN
        TranslationService translationService = new InMemoryTranslationService();
        translationService.addCode("CODE", Locale.CANADA, "RESULT");

        // WHEN
        Translation translation = translationService.translate("CODE", Locale.CANADA);

        // THEN
        Assert.assertTrue(translation.getLocale().isPresent());
        Assert.assertEquals(Locale.CANADA, translation.getLocale().get());
        Assert.assertEquals("CODE", translation.getCode());
        Assert.assertEquals("RESULT", translation.getTranslation());
    }

    @Test
    public void franceDefineFrench() {
        // GIVEN
        TranslationService translationService = new InMemoryTranslationService();
        translationService.addCode("CODE", Locale.FRANCE, "RESULT");

        // WHEN
        Translation translation = translationService.translate("CODE", Locale.FRENCH);

        // THEN
        Assert.assertTrue(translation.getLocale().isPresent());
        Assert.assertEquals(Locale.FRENCH, translation.getLocale().get());
        Assert.assertEquals("CODE", translation.getCode());
        Assert.assertEquals("RESULT", translation.getTranslation());
    }

    @Test
    public void franceUseFrench() {
        // GIVEN
        TranslationService translationService = new InMemoryTranslationService();
        translationService.addCode("CODE", Locale.FRENCH, "RESULT");

        // WHEN
        Translation translation = translationService.translate("CODE", Locale.FRANCE);

        // THEN
        Assert.assertTrue(translation.getLocale().isPresent());
        Assert.assertEquals(Locale.FRENCH, translation.getLocale().get());
        Assert.assertEquals("CODE", translation.getCode());
        Assert.assertEquals("RESULT", translation.getTranslation());
    }

}
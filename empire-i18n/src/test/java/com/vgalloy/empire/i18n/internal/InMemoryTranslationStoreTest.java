package com.vgalloy.empire.i18n.internal;

import com.vgalloy.empire.i18n.Translation;
import com.vgalloy.empire.i18n.TranslationKey;
import com.vgalloy.empire.i18n.TranslationStore;
import java.util.Locale;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Vincent Galloy on 07/04/18.
 *
 * @author Vincent Galloy
 */
public final class InMemoryTranslationStoreTest {

  @Test
  void noTranslation() {
    // GIVEN
    final var translationDao = new InMemoryTranslationStore();
    final var key = new TranslationKey("CODE", Locale.CANADA);

    // WHEN
    final var translation = translationDao.translate(key);

    // THEN
    Assertions.assertTrue(translation.getKey().isDefault());
    Assertions.assertEquals("CODE", translation.getValue());
  }

  @Test
  void oneTranslation() {
    // GIVEN
    final TranslationStore translationDao = new InMemoryTranslationStore();
    final var key = new TranslationKey("CODE", Locale.CANADA);
    translationDao.createOrUpdateExact(new Translation(key, "RESULT"));

    // WHEN
    final Translation translation = translationDao.translate(key);

    // THEN
    Assertions.assertFalse(translation.getKey().isDefault());
    Assertions.assertEquals(Locale.CANADA, translation.getKey().locale());
    Assertions.assertEquals("CODE", translation.getKey().code());
    Assertions.assertEquals("RESULT", translation.getValue());
  }

  @Test
  void franceDefineFrench() {
    // GIVEN
    final TranslationStore translationDao = new InMemoryTranslationStore();
    final var key = new TranslationKey("CODE", Locale.FRANCE);
    translationDao.createOrUpdateRecursivelyIfMissing(new Translation(key, "RESULT"));

    // WHEN
    final Translation translation =
        translationDao.translate(new TranslationKey("CODE", Locale.FRENCH));

    // THEN
    Assertions.assertFalse(translation.getKey().isDefault());
    Assertions.assertEquals(Locale.FRENCH, translation.getKey().locale());
    Assertions.assertEquals("CODE", translation.getKey().code());
    Assertions.assertEquals("RESULT", translation.getValue());
  }

  @Test
  void franceUseFrench() {
    // GIVEN
    final TranslationStore translationDao = new InMemoryTranslationStore();
    final var key = new TranslationKey("CODE", Locale.FRENCH);
    translationDao.createOrUpdateRecursivelyIfMissing(new Translation(key, "RESULT"));

    // WHEN
    final Translation translation =
        translationDao.translate(new TranslationKey("CODE", Locale.FRANCE));

    // THEN
    Assertions.assertFalse(translation.getKey().isDefault());
    Assertions.assertEquals(Locale.FRENCH, translation.getKey().locale());
    Assertions.assertEquals("CODE", translation.getKey().code());
    Assertions.assertEquals("RESULT", translation.getValue());
  }

  @Test
  void addIfMissingDoesNotOverridePreviousValue() {
    // GIVEN
    final TranslationStore translationDao = new InMemoryTranslationStore();
    final var frCA = new TranslationKey("CODE", Locale.CANADA_FRENCH);
    translationDao.createOrUpdateRecursivelyIfMissing(new Translation(frCA, "fr_CA"));
    final var frFR = new TranslationKey("CODE", Locale.FRANCE);
    translationDao.createOrUpdateRecursivelyIfMissing(new Translation(frFR, "fr_FR"));

    // WHEN
    final Translation translation =
        translationDao.translate(new TranslationKey("CODE", new Locale("fr", "ze")));

    // THEN
    Assertions.assertFalse(translation.getKey().isDefault());
    Assertions.assertEquals(Locale.FRENCH, translation.getKey().locale());
    Assertions.assertEquals("CODE", translation.getKey().code());
    Assertions.assertEquals("fr_CA", translation.getValue());
  }

  @Test
  void addForceOverridePreviousValue() {
    // GIVEN
    final TranslationStore translationDao = new InMemoryTranslationStore();
    final var frCA = new TranslationKey("CODE", Locale.CANADA_FRENCH);
    translationDao.createOrUpdateRecursivelyIfMissing(new Translation(frCA, "fr_CA"));
    final var frFR = new TranslationKey("CODE", Locale.FRANCE);
    translationDao.createOrUpdateRecursivelyForce(new Translation(frFR, "fr_FR"));

    // WHEN
    final Translation translation =
        translationDao.translate(new TranslationKey("CODE", new Locale("fr", "ze")));

    // THEN
    Assertions.assertFalse(translation.getKey().isDefault());
    Assertions.assertEquals(Locale.FRENCH, translation.getKey().locale());
    Assertions.assertEquals("CODE", translation.getKey().code());
    Assertions.assertEquals("fr_FR", translation.getValue());
  }
}

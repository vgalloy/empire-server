package com.vgalloy.empire.i18n.internal;

import com.vgalloy.empire.i18n.Translation;
import com.vgalloy.empire.i18n.TranslationKey;
import com.vgalloy.empire.i18n.TranslationStore;
import java.util.Locale;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Vincent Galloy on 07/04/18.
 *
 * @author Vincent Galloy
 */
public final class InMemoryTranslationStoreTest {

  @Test
  public void noTranslation() {
    // GIVEN
    final var translationDao = new InMemoryTranslationStore();
    final var key = new TranslationKey("CODE", Locale.CANADA);

    // WHEN
    final var translation = translationDao.translate(key);

    // THEN
    Assert.assertTrue(translation.getKey().isDefault());
    Assert.assertEquals("CODE", translation.getValue());
  }

  @Test
  public void oneTranslation() {
    // GIVEN
    final TranslationStore translationDao = new InMemoryTranslationStore();
    final var key = new TranslationKey("CODE", Locale.CANADA);
    translationDao.createOrUpdateExact(new Translation(key, "RESULT"));

    // WHEN
    final Translation translation = translationDao.translate(key);

    // THEN
    Assert.assertFalse(translation.getKey().isDefault());
    Assert.assertEquals(Locale.CANADA, translation.getKey().locale());
    Assert.assertEquals("CODE", translation.getKey().code());
    Assert.assertEquals("RESULT", translation.getValue());
  }

  @Test
  public void franceDefineFrench() {
    // GIVEN
    final TranslationStore translationDao = new InMemoryTranslationStore();
    final var key = new TranslationKey("CODE", Locale.FRANCE);
    translationDao.createOrUpdateRecursivelyIfMissing(new Translation(key, "RESULT"));

    // WHEN
    final Translation translation =
        translationDao.translate(new TranslationKey("CODE", Locale.FRENCH));

    // THEN
    Assert.assertFalse(translation.getKey().isDefault());
    Assert.assertEquals(Locale.FRENCH, translation.getKey().locale());
    Assert.assertEquals("CODE", translation.getKey().code());
    Assert.assertEquals("RESULT", translation.getValue());
  }

  @Test
  public void franceUseFrench() {
    // GIVEN
    final TranslationStore translationDao = new InMemoryTranslationStore();
    final var key = new TranslationKey("CODE", Locale.FRENCH);
    translationDao.createOrUpdateRecursivelyIfMissing(new Translation(key, "RESULT"));

    // WHEN
    final Translation translation =
        translationDao.translate(new TranslationKey("CODE", Locale.FRANCE));

    // THEN
    Assert.assertFalse(translation.getKey().isDefault());
    Assert.assertEquals(Locale.FRENCH, translation.getKey().locale());
    Assert.assertEquals("CODE", translation.getKey().code());
    Assert.assertEquals("RESULT", translation.getValue());
  }

  @Test
  public void addIfMissingDoesNotOverridePreviousValue() {
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
    Assert.assertFalse(translation.getKey().isDefault());
    Assert.assertEquals(Locale.FRENCH, translation.getKey().locale());
    Assert.assertEquals("CODE", translation.getKey().code());
    Assert.assertEquals("fr_CA", translation.getValue());
  }

  @Test
  public void addForceOverridePreviousValue() {
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
    Assert.assertFalse(translation.getKey().isDefault());
    Assert.assertEquals(Locale.FRENCH, translation.getKey().locale());
    Assert.assertEquals("CODE", translation.getKey().code());
    Assert.assertEquals("fr_FR", translation.getValue());
  }
}

package com.vgalloy.empire.i18n;

import java.util.List;
import java.util.Locale;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by Vincent Galloy on 17/11/18.
 *
 * @author Vincent Galloy
 */
final class TranslationKeyTest {

  @Test
  void notDefault() {
    // GIVEN
    final TranslationKey translationKey = new TranslationKey("CODE", new Locale("fr"));

    // WHEN
    final boolean result = translationKey.isDefault();

    // THEN
    Assertions.assertFalse(result);
  }

  @Test
  void isDefault() {
    // GIVEN
    final TranslationKey translationKey = new TranslationKey("CODE", new Locale(""));

    // WHEN
    final boolean result = translationKey.isDefault();

    // THEN
    Assertions.assertTrue(result);
  }

  @Test
  void parent() {
    // GIVEN
    final TranslationKey translationKey = new TranslationKey("CODE", Locale.CANADA_FRENCH);

    // WHEN
    final TranslationKey parent = translationKey.getParent();

    // THEN
    Assertions.assertFalse(parent.isDefault());
    Assertions.assertEquals(Locale.FRENCH, parent.locale());
    Assertions.assertEquals("CODE", parent.code());
  }

  @Test
  void root() {
    // GIVEN
    final TranslationKey translationKey = new TranslationKey("CODE", Locale.CANADA_FRENCH);

    // WHEN
    final TranslationKey root = translationKey.getParent().getParent();

    // THEN
    Assertions.assertTrue(root.isDefault());
    Assertions.assertEquals(new Locale(""), root.locale());
    Assertions.assertEquals(root, root.getParent());
  }

  @Test
  void hierarchy() {
    // GIVEN
    final TranslationKey translationKey = new TranslationKey("CODE", Locale.CANADA_FRENCH);

    // WHEN
    final List<TranslationKey> hierarchy = translationKey.getHierarchy();

    // THEN
    Assertions.assertEquals(3, hierarchy.size());
    Assertions.assertEquals(translationKey, hierarchy.get(0));
    Assertions.assertTrue(hierarchy.get(2).isDefault());

    Assertions.assertEquals("fr-CA", hierarchy.get(0).locale().toLanguageTag());
    Assertions.assertEquals("fr", hierarchy.get(1).locale().toLanguageTag());
    Assertions.assertEquals("und", hierarchy.get(2).locale().toLanguageTag());
  }

  @Test
  void getParentWithScriptAndCountry() {
    // GIVEN
    final var locale = Locale.forLanguageTag("fr-Cyrl-FR");
    final TranslationKey translationKey = new TranslationKey("CODE", locale);

    // WHEN
    final TranslationKey result = translationKey.getParent();

    // THEN
    Assertions.assertEquals("fr-Cyrl", result.locale().toLanguageTag());
  }

  @Test
  void getParentWithScript() {
    // GIVEN
    final var locale = Locale.forLanguageTag("fr-Cyrl");
    final TranslationKey translationKey = new TranslationKey("CODE", locale);

    // WHEN
    final TranslationKey result = translationKey.getParent();

    // THEN
    Assertions.assertEquals("fr", result.locale().toLanguageTag());
  }

  @Test
  void hierarchyWithScript() {
    // GIVEN
    final var locale = Locale.forLanguageTag("fr-Cyrl-FR");
    final TranslationKey translationKey = new TranslationKey("CODE", locale);

    // WHEN
    final List<TranslationKey> hierarchy = translationKey.getHierarchy();

    // THEN
    Assertions.assertEquals(4, hierarchy.size());
    Assertions.assertEquals(translationKey, hierarchy.get(0));
    Assertions.assertTrue(hierarchy.get(3).isDefault());

    Assertions.assertEquals("fr-Cyrl-FR", hierarchy.get(0).locale().toLanguageTag());
    Assertions.assertEquals("fr-Cyrl", hierarchy.get(1).locale().toLanguageTag());
    Assertions.assertEquals("fr", hierarchy.get(2).locale().toLanguageTag());
    Assertions.assertEquals("und", hierarchy.get(3).locale().toLanguageTag());
  }

  @Test
  void comparable() {
    // GIVEN
    final TranslationKey frFr = new TranslationKey("CODE", Locale.FRANCE);
    final TranslationKey frCA = new TranslationKey("CODE", Locale.CANADA_FRENCH);

    // WHEN
    final int result = frFr.compareTo(frCA);

    // THEN
    Assertions.assertTrue(0 < result);
  }
}

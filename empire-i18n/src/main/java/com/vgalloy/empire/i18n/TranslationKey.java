package com.vgalloy.empire.i18n;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by Vincent Galloy on 17/11/18.
 *
 * @author Vincent Galloy
 */
public class TranslationKey implements Comparable<TranslationKey> {

    private static final Locale EMPTY_LOCALE = new Locale("");
    private static final Comparator<TranslationKey> COMPARATOR = Comparator.comparing((TranslationKey key) -> key.locale.toLanguageTag())
        .thenComparing(TranslationKey::code);

    private final String code;
    private final Locale locale;

    /**
     * Constructor.
     *
     * @param code   the code, not null
     * @param locale the locale associated to the code
     */
    public TranslationKey(final String code, final Locale locale) {
        this.code = Objects.requireNonNull(code, "code");
        this.locale = Objects.requireNonNull(locale, "locale");
    }

    /**
     * The code.
     *
     * @return code, not null
     */
    public String code() {
        return code;
    }

    /**
     * The locale.
     *
     * @return the locale associated to the code, not null
     */
    public Locale locale() {
        return locale;
    }

    /**
     * True if the getKey representing the default value for the code.
     *
     * @return true if the {@link #locale()} is empty
     */
    public boolean isDefault() {
        return EMPTY_LOCALE.equals(this.locale);
    }

    /**
     * According to BCP 47 specification {@link Locale} is divided into
     * several part. This method return a {@link TranslationKey getKey} with the
     * parent of the current locale.
     * Example : 'fr_FR' will be 'fr'
     *
     * @return a new instance of {@link TranslationKey getKey} will the parent locale
     */
    public TranslationKey getParent() {
        if (isDefault()) {
            return this;
        }
        final var tagLang = locale.toLanguageTag().split("-");
        final var newTagLang = Arrays.stream(tagLang)
            .limit(tagLang.length - 1)
            .collect(Collectors.joining("-"));
        return new TranslationKey(code, Locale.forLanguageTag(newTagLang));
    }

    /**
     * Return the current getKey and all parent order ascending.
     * The first element of the list must this the current getKey and the last
     * must be the {@link #isDefault() default} getKey.
     *
     * @return the list of all parent
     */
    public List<TranslationKey> getHierarchy() {
        final List<TranslationKey> result = new LinkedList<>();
        var localKey = this;
        do {
            result.add(localKey);
            localKey = localKey.getParent();
        } while (!localKey.isDefault());
        result.add(localKey);
        return result;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final TranslationKey that = (TranslationKey) o;
        return Objects.equals(code, that.code) &&
            Objects.equals(locale, that.locale);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, locale);
    }

    @Override
    public String toString() {
        return locale.toLanguageTag() + '#' + code;
    }

    @Override
    public int compareTo(final TranslationKey o) {
        return COMPARATOR.compare(this, o);
    }
}

package com.vgalloy.empire.i18n.internal;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.vgalloy.empire.i18n.Translation;
import com.vgalloy.empire.i18n.TranslationKey;
import com.vgalloy.empire.i18n.TranslationStore;

/**
 * Created by Vincent Galloy on 07/04/18.
 *
 * @author Vincent Galloy
 */
@Service
class InMemoryTranslationStore implements TranslationStore {

    private final Map<TranslationKey, String> translationTable = new ConcurrentHashMap<>();

    @Override
    public Translation translate(final TranslationKey translationKey) {
        Objects.requireNonNull(translationKey, "translationKey");

        var localKey = translationKey;
        var value = translationTable.get(localKey);
        while (Objects.isNull(value) && !localKey.isDefault()) {
            localKey = localKey.getParent();
            value = translationTable.get(localKey);
        }
        if (localKey.isDefault() && Objects.isNull(value)) {
            return new Translation(localKey, translationKey.code());
        }
        return new Translation(localKey, value);
    }

    @Override
    public void createOrUpdateExact(final Translation translation) {
        run(translation, StandardUpdateStrategy.ONLY_ONE);
    }

    @Override
    public void createOrUpdateRecursivelyIfMissing(final Translation translation) {
        run(translation, StandardUpdateStrategy.IF_MISSING);
    }

    @Override
    public void createOrUpdateRecursivelyForce(final Translation translation) {
        run(translation, StandardUpdateStrategy.ALL);
    }

    /**
     * Run the strategy on the provided translation.
     *
     * @param translation    the translation
     * @param updateStrategy the update strategy
     */
    private void run(final Translation translation, final UpdateStrategy updateStrategy) {
        translation.getKey().getHierarchy().forEach(key -> {
            final Map.Entry<TranslationKey, String> currentTranslation = new AbstractMap.SimpleEntry<>(key, this.translationTable.get(key));
            updateStrategy.update(translation, currentTranslation)
                .ifPresent(newValue -> this.translationTable.put(key, newValue));
        });
    }

    private interface UpdateStrategy {
        /**
         * Update (or not) the value of the translation of the current translation context.
         *
         * @param newTranslation     the new translation
         * @param currentTranslation the current translation
         * @return An empty optional if the current context shouldn't be change of the new value
         */
        Optional<String> update(Translation newTranslation, Map.Entry<TranslationKey, String> currentTranslation);
    }

    private enum StandardUpdateStrategy implements UpdateStrategy {
        ONLY_ONE {
            @Override
            public Optional<String> update(final Translation newTranslation, final Map.Entry<TranslationKey, String> currentTranslation) {
                if (Objects.equals(newTranslation.getKey(), currentTranslation.getKey())) {
                    return Optional.of(newTranslation.getValue());
                }
                return Optional.empty();
            }
        },
        IF_MISSING {
            @Override
            public Optional<String> update(final Translation newTranslation, final Map.Entry<TranslationKey, String> currentTranslation) {
                if (Objects.equals(newTranslation.getKey(), currentTranslation.getKey())) {
                    return Optional.of(newTranslation.getValue());
                }
                if (Objects.isNull(currentTranslation.getValue())) {
                    return Optional.of(newTranslation.getValue());
                }
                return Optional.empty();
            }
        },
        ALL {
            @Override
            public Optional<String> update(final Translation newTranslation, final Map.Entry<TranslationKey, String> currentTranslation) {
                return Optional.of(newTranslation.getValue());
            }
        }
    }
}

package fr.univtours.examplanner.translations;

import fr.univtours.examplanner.Storage;
import fr.univtours.examplanner.utils.Json;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Objects;

public class Translation {

	/**
	 * Instance pour le singleton
	 */
	private static @Nullable Translation instance;

	/**
	 * Liste des traductions disponibles
	 */
	private @Nullable HashMap<String, String> loadedTranslations = null;

	// La classe est un singleton
	private Translation() {
	}

	/**
	 * Crée un singleton de la classe Translation<br>
	 * Permet de ne pas avoir à charger plusieurs fois le fichier de traduction
	 *
	 * @return instance de la classe Translation
	 */
	private static @NotNull Translation getInstance() {
		if ( null == instance ) {
			instance = new Translation();
		}
		return instance;
	}

	/**
     * Obtenir une traduction
     *
     * @param key La clé de la traduction
     * @return La traduction
     */
    public static @NotNull String get( @NotNull String key ) {
        if ( null == getInstance().loadedTranslations ) {
            getInstance().loadLanguage(Storage.getLanguage());
        }
        return Objects.requireNonNull(getInstance().loadedTranslations).getOrDefault(key, key);
    }

    /**
     * Charge le fichier de traduction correspondant à la langue passée en paramètre
     *
     * @param language Langue à charger
     */
    private void loadLanguage( @NotNull SupportedLanguages language ) {
        if ( null == loadedTranslations ) {
            loadedTranslations = new HashMap<>();
        }
		loadedTranslations.clear();
		loadedTranslations.putAll(Json.JsonObjectToHashMap(Json.parse("translations/" + language.getFileName())));
	}

    /**
     * Retourne la clé de la traduction
     *
     * @param translation La traduction
     * @return La clé de la traduction
     */
    public static @NotNull String rollback( @NotNull String translation ) {
        if ( Objects.isNull(getInstance().loadedTranslations) ) {
            getInstance().loadLanguage(Storage.getLanguage());
        }
        for ( String key : getInstance().loadedTranslations.keySet() ) {
            if ( Objects.requireNonNull(getInstance().loadedTranslations).get(key).equals(translation) ) {
                return key;
            }
        }
        return translation;
    }

    /**
     * Change la langue courante
     *
     * @param language Langue à charger
     */
    public static void setLanguage( @NotNull SupportedLanguages language ) {
        getInstance().loadLanguage(language);
        Storage.setLanguage(language);
    }

}
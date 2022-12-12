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
		if (instance == null) {
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
	public static @NotNull String get(@NotNull String key) {
		Translation instance = getInstance();
		if (instance.loadedTranslations == null) {
			instance.loadLanguage(Storage.getCurrentLanguage());
		}
		return Objects.requireNonNull(getInstance().loadedTranslations).getOrDefault(key, key);
	}

	/**
	 * Change la langue courante
	 *
	 * @param language Langue à charger
	 */
	public static void setLanguage(@NotNull SupportedLanguages language) {
		getInstance().loadLanguage(language);
		Storage.setCurrentLanguage(language);
	}

	/**
	 * Charge le fichier de traduction correspondant à la langue passée en paramètre
	 *
	 * @param language Langue à charger
	 */
	private void loadLanguage(@NotNull SupportedLanguages language) {
		if (loadedTranslations == null) {
			loadedTranslations = new HashMap<>();
		}
		loadedTranslations.clear();
		loadedTranslations.putAll(Json.JsonObjectToHashMap(Json.parse("translations/" + language.getFileName())));
	}

}
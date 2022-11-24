package fr.univtours.examplanner.translations;

import fr.univtours.examplanner.Storage;

import java.util.HashMap;
import java.util.List;

/**
 * loadedLanguage:
 * Contient en clé le nom de la traduction et en valeur, la traduction.
 * Est chargé à partir d'un fichier nommé selon les conventions internationales (A PRECISER) dans le dossier renseigner
 */
public class Translation {

	private static Translation instance;
	private final HashMap<String, String> loadedTranslations = new HashMap<>();

	private Translation() {
		Storage.currentLanguageProperty().addListener((observable, oldValue, newValue) -> {
			loadLanguage(newValue);
		});
	}

	private static Translation getInstance() {
		if (instance == null) {
			instance = new Translation();
		}
		return instance;
	}

	public static List<String> getLanguages() {
		return SupportedLanguages.getAll();
	}

	public static String get(String key) {
		return getInstance().loadedTranslations.getOrDefault(key, key);
	}

	private void loadLanguage(SupportedLanguages language) {
		loadedTranslations.clear();
		// Todo load from file
	}

}
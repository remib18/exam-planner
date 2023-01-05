package fr.univtours.examplanner.translations;

import org.jetbrains.annotations.NotNull;

/**
 * Une énumération des langues supportées par l'application.
 */
public enum SupportedLanguages {
	French,
	UsEnglish;

	/**
	 * Lit les langues supportées à leurs fichiers de traduction.
	 *
	 * @return Le nom du fichier de traduction
	 */
	public @NotNull String getFileName() {
		return switch (this) {
			case French -> "fr-fr.json";
			case UsEnglish -> "en-us.json";
		};
	}

	/**
	 * Lit les langues supportées à leurs abréviations
	 *
	 * @return L'abréviation de la langue
	 */
	public String shortHand() {
		return switch (this) {
			case French -> "fr";
			case UsEnglish -> "en";
		};
	}
}
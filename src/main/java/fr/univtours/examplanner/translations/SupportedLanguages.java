package fr.univtours.examplanner.translations;

import org.jetbrains.annotations.NotNull;

public enum SupportedLanguages {
	French,
	UsEnglish;

	/**
	 * Lie les langues supportées à leurs fichiers de traduction.
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
	 * Lie les langues supportées à leurs abréviations
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
package fr.univtours.examplanner.enums;

import fr.univtours.examplanner.translations.Translation;

/**
 * Civilité d'une personne
 */
public enum Civility {
	Men,
	Women,
	Other;

	@Override
	public String toString() {
		return Translation.get("civility." + name());
	}
}
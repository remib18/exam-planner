package fr.univtours.examplanner.enums;

import fr.univtours.examplanner.translations.Translation;

/**
 * Degré d'étude d'une formation
 */
public enum Degree {
	Bachelor,
	Master,
	Doctorate;

	@Override
	public String toString() {
		return Translation.get("computerEnvironment." + name());
	}
}
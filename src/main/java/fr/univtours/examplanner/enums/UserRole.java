package fr.univtours.examplanner.enums;

import fr.univtours.examplanner.translations.Translation;

/**
 * Rôle d'un utilisateur
 */
public enum UserRole {
	Manager,
	Department,
	Schooling;

	@Override
	public String toString() {
		return Translation.get("computerEnvironment." + name());
	}
}
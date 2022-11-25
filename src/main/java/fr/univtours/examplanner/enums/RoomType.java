package fr.univtours.examplanner.enums;

import fr.univtours.examplanner.translations.Translation;

/**
 * Type de salle
 */
public enum RoomType {
	Amphitheater,
	ComputerRoom,
	Laboratory,
	Library,
	Office;

	@Override
	public String toString() {
		return Translation.get("computerEnvironment." + name());
	}
}


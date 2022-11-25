package fr.univtours.examplanner.enums;

import fr.univtours.examplanner.translations.Translation;

/**
 * Configuration de la salle
 */
public enum RoomEquipment {
	Projector,
	Speaker,
	Board,
	Webcam;

	@Override
	public String toString() {
		return Translation.get("computerEnvironment." + name());
	}
}

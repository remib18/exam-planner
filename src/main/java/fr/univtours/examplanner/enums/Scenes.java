package fr.univtours.examplanner.enums;

import fr.univtours.examplanner.translations.Translation;

public enum Scenes {
	Dashboard,
	Department,
	Exam,
	Group,
	Login,
	Manager,
	Room,
	Slot,
	User;

	@Override
	public String toString() {
		return Translation.get("computerEnvironment." + name());
	}
}

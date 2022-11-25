package fr.univtours.examplanner.enums;

import fr.univtours.examplanner.translations.Translation;

/**
 * Spécifie des contraintes sur l'environnement de travail d'un utilisateur
 */
public enum ComputerEnvironment {
	OfficeApplication,
	InternetAccess,
	LinuxEnvironment,
	WindowsEnvironment,
	MacOsEnvironment,
	ProgrammingApplication,
	PhysicsApplication,
	MathApplication,
	ChemistryApplication;

	@Override
	public String toString() {
		return Translation.get("computerEnvironment." + name());
	}
}

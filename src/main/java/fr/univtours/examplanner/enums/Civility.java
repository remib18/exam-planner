package fr.univtours.examplanner.enums;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;

/**
 * Civilité d'une personne
 */
public enum Civility {
	Men,
	Women,
	Other;

	public static Civility parse( @NotNull String role ) throws ParseException {
		return switch ( role ) {
			case "Men" -> Civility.Men;
			case "Women" -> Civility.Women;
			case "Other" -> Civility.Other;
			default -> throw new ParseException("Role " + role + " doesn't exist on Civility.", 1);
		};
	}


	@Override
	public String toString() {
		return name();
	}
}
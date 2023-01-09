package fr.univtours.examplanner.enums;

import fr.univtours.examplanner.translations.Translation;
import javafx.util.StringConverter;
import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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

	public static @NotNull StringConverter< Civility > getConverter() {
		return new CivilityStringConverter();
	}

	public static List< Civility > getOptions() {
		List< Civility > list = new ArrayList<>();
		Collections.addAll(list, values());
		return list;
	}


	@Override
	public String toString() {
		return name();
	}

	public static Civility fromTranslation( @NotNull String translation ) {
		return switch ( Translation.rollback(translation) ) {
			case "manager.civility.men" -> Civility.Men;
			case "manager.civility.women" -> Civility.Women;
			case "manager.civility.other" -> Civility.Other;
			default -> null;
		};
	}

	public String translate() {
		return switch ( this ) {
			case Men -> Translation.get("manager.civility.men");
			case Women -> Translation.get("manager.civility.women");
			case Other -> Translation.get("manager.civility.other");
		};
	}

	private static class CivilityStringConverter extends StringConverter< Civility > {

		@Override
		public String toString( Civility civility ) {
			if ( Objects.isNull(civility) ) {
				return null;
			}
			return civility.translate();
		}

		@Override
		public Civility fromString( String s ) {
			if ( Objects.isNull(s) ) {
				return null;
			}
			return Civility.fromTranslation(s);
		}
	}
}
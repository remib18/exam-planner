package fr.univtours.examplanner.enums;

import fr.univtours.examplanner.translations.Translation;

import java.text.ParseException;

/**
 * Degré d'étude d'une formation
 */
public enum Degree {
    Bachelor,
    Master,
    Doctorate;

    public static Degree parse( String degree ) throws ParseException {
        return switch ( degree ) {
            case "Bachelor" -> Bachelor;
            case "Master" -> Master;
            case "Doctorate" -> Doctorate;
            default -> throw new ParseException("Degree " + degree + " doesn't exist on Degree.", 1);
        };
    }

    @Override
    public String toString() {
        return Translation.get("computerEnvironment." + name());
    }
}
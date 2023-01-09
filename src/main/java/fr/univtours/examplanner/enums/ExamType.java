package fr.univtours.examplanner.enums;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;

/**
 * Type d'examen (continu ou partiel)
 */
public enum ExamType {
    Continuous ("Continuous"),
    Final("Final");

    private final String type;

    ExamType(String type){
        this.type = type;
    }

    public String getTypeString() {
        return type;
    }

    public static ExamType parse( @NotNull String type ) throws ParseException {
        return switch ( type ) {
            case "Continuous" -> ExamType.Continuous;
            case "Final" -> ExamType.Final;
            default -> throw new ParseException("Type " + type + " doesn't exist on ExamType.", 1);
        };
    }

}


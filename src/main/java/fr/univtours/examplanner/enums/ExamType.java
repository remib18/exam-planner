package fr.univtours.examplanner.enums;

import fr.univtours.examplanner.translations.Translation;
import javafx.util.StringConverter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Type d'examen (continu ou partiel)
 */
public enum ExamType {
    Continuous("Continuous"),
    Final("Final");

    private final String type;

    ExamType( String type ) {
        this.type = type;
    }

    public static StringConverter< ExamType > getConverter() {
        return new ExamTypeStringConverter();
    }

    public static ExamType fromTranslation( @NotNull String translation ) {
        return switch ( Translation.rollback(translation) ) {
            case "exam.type.continuous" -> Continuous;
            case "exam.type.final" -> Final;
            default -> null;
        };
    }

    public static ExamType setTypeByString( String type ) {
        switch ( type ) {
            case "Continuous":
                return ExamType.Continuous;
            case "Final":
                return ExamType.Final;
        }
        throw new UnsupportedOperationException("Should be unreachable");
    }

    public String getTypeString() {
        return type;
    }

    public String translate() {
        return switch ( this ) {
            case Continuous -> Translation.get("exam.type.continuous");
            case Final -> Translation.get("exam.type.final");
        };
    }

    private static class ExamTypeStringConverter extends StringConverter< ExamType > {

        @Override
        public String toString( ExamType object ) {
            if ( Objects.isNull(object) ) {
                return null;
            }
            return object.translate();
        }

        @Override
        public ExamType fromString( String s ) {
            if ( Objects.isNull(s) ) {
                return null;
            }
            return ExamType.fromTranslation(s);
        }
    }
}


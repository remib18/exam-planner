package fr.univtours.examplanner.enums;

import javafx.util.StringConverter;
import org.jetbrains.annotations.NotNull;

/**
 * Configuration de la salle
 */
public enum MockupYear {
    One,
    Two,
    Tree;

    public static MockupYear parse( @NotNull String equipment ) {
        return switch ( equipment ) {
            case "1" -> One;
            case "2" -> Two;
            case "3" -> Tree;
            default -> null;
        };
    }

    @Override
    public String toString() {
        return switch ( this ) {
            case One -> "1";
            case Two -> "2";
            case Tree -> "3";
        };
    }

    private static class MockupYearStringConverter extends StringConverter< MockupYear > {

        @Override
        public String toString( MockupYear object ) {
            return object.toString();
        }

        @Override
        public MockupYear fromString( String s ) {
            return MockupYear.parse(s);
        }
    }
}

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
 * Rôle d'un utilisateur
 */
public enum UserRole {
    Manager,
    Department,
    Schooling;

    public static UserRole parse( @NotNull String role ) throws ParseException {
        return switch ( role ) {
            case "Manager" -> UserRole.Manager;
            case "Department" -> UserRole.Department;
            case "Schooling" -> UserRole.Schooling;
            default -> throw new ParseException("Role " + role + " doesn't exist on UserRole.", 1);
        };
    }

    @Override
    public String toString() {
        return name();
    }

    public static UserRole fromTranslation( @NotNull String translation ) {
        return switch ( Translation.rollback(translation) ) {
            case "feature.user.role.manager" -> UserRole.Manager;
            case "feature.user.role.department" -> UserRole.Department;
            case "feature.user.role.schooling" -> UserRole.Schooling;
            default -> null;
        };
    }

    public static StringConverter< UserRole > getConverter() {
        return new UserRoleStringConverter();
    }

    public static List< UserRole > getOptions() {
        List< UserRole > options = new ArrayList<>();
        Collections.addAll(options, values());
        return options;
    }

    public String translate() {
        return switch ( this ) {
            case Manager -> Translation.get("feature.user.role.manager");
            case Department -> Translation.get("feature.user.role.department");
            case Schooling -> Translation.get("feature.user.role.schooling");
        };
    }

    private static class UserRoleStringConverter extends StringConverter< UserRole > {

        @Override
        public String toString( UserRole object ) {
            if ( Objects.isNull(object) ) {
                return null;
            }
            return object.translate();
        }

        @Override
        public UserRole fromString( String s ) {
            if ( Objects.isNull(s) ) {
                return null;
            }
            return UserRole.fromTranslation(s);
        }
    }
}
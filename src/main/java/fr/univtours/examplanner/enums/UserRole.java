package fr.univtours.examplanner.enums;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;

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
}
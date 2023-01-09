package fr.univtours.examplanner.enums;

import org.jetbrains.annotations.NotNull;


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

    public static ComputerEnvironment parse( @NotNull String environment ) {
        return switch ( environment ) {
            case "OfficeApplication" -> ComputerEnvironment.OfficeApplication;
            case "InternetAccess" -> ComputerEnvironment.InternetAccess;
            case "LinuxEnvironment" -> ComputerEnvironment.LinuxEnvironment;
            case "WindowsEnvironment" -> ComputerEnvironment.WindowsEnvironment;
            case "MacOsEnvironment" -> ComputerEnvironment.MacOsEnvironment;
            case "ProgrammingApplication" -> ComputerEnvironment.ProgrammingApplication;
            case "PhysicsApplication" -> ComputerEnvironment.PhysicsApplication;
            case "MathApplication" -> ComputerEnvironment.MathApplication;
            case "ChemistryApplication" -> ComputerEnvironment.ChemistryApplication;
            default -> null;
        };
    }

    @Override
    public String toString() {
        return name();
    }


}

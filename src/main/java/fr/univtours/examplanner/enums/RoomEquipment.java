package fr.univtours.examplanner.enums;

import org.jetbrains.annotations.NotNull;

/**
 * Configuration de la salle
 */
public enum RoomEquipment {
    Projector,
    Speaker,
    Board,
    Webcam;

    public static RoomEquipment parse( @NotNull String equipment ) {
        return switch ( equipment ) {
            case "Projector" -> RoomEquipment.Projector;
            case "Speaker" -> RoomEquipment.Speaker;
            case "Board" -> RoomEquipment.Board;
            case "Webcam" -> RoomEquipment.Webcam;
            default -> null;
        };
    }

    @Override
    public String toString() {
        return name();
    }
}

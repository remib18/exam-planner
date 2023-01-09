package fr.univtours.examplanner.enums;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;

/**
 * Configuration de la salle
 */
public enum RoomEquipment {
    Projector,
    Speaker,
    Board,
    Webcam;

    public static RoomEquipment parse( @NotNull String equipment ) throws ParseException {
        return switch ( equipment ) {
            case "Projector" -> RoomEquipment.Projector;
            case "Speaker" -> RoomEquipment.Speaker;
            case "Board" -> RoomEquipment.Board;
            case "Webcam" -> RoomEquipment.Webcam;
            default -> throw new ParseException("Equipment" + equipment + " doesn't exist on RoomEquipment.", 1);
        };
    }

    @Override
    public String toString() {
        return name();
    }
}

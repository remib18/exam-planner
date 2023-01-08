package fr.univtours.examplanner.enums;

import org.jetbrains.annotations.NotNull;

import java.text.ParseException;


/**
 * Type de salle
 */
public enum RoomType {
    Amphitheater,
    ComputerRoom,
    Laboratory,
    Library,
    Office;

    public static RoomType parse( @NotNull String type ) throws ParseException {
        return switch ( type ) {
            case "Amphitheater" -> RoomType.Amphitheater;
            case "ComputerRoom" -> RoomType.ComputerRoom;
            case "Laboratory" -> RoomType.Laboratory;
            case "Library" -> RoomType.Library;
            case "Office" -> RoomType.Office;
            default -> throw new ParseException("Type " + type + " doesn't exist on RoomType.", 1);
        };
    }

    @Override
    public String toString() {
        return name();
    }
}






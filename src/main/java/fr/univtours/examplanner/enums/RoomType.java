package fr.univtours.examplanner.enums;

import fr.univtours.examplanner.translations.Translation;
import javafx.util.StringConverter;
import org.jetbrains.annotations.NotNull;

import java.text.ParseException;
import java.util.Objects;


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

    public static StringConverter< RoomType > getConverter() {
        return new RoomTypeStringConverter();
    }

    @Override
    public String toString() {
        return name();
    }

    public static RoomType fromTranslation( @NotNull String translation ) {
        return switch ( Translation.rollback(translation) ) {
            case "room.type.amphitheater" -> Amphitheater;
            case "room.type.computer-room" -> ComputerRoom;
            case "room.type.laboratory" -> Laboratory;
            case "room.type.library" -> Library;
            case "room.type.office" -> Office;
            default -> null;
        };
    }

    public String translate() {
        return switch ( this ) {
            case Amphitheater -> Translation.get("room.type.amphitheater");
            case ComputerRoom -> Translation.get("room.type.computer-room");
            case Laboratory -> Translation.get("room.type.laboratory");
            case Library -> Translation.get("room.type.library");
            case Office -> Translation.get("room.type.office");
        };
    }

    private static class RoomTypeStringConverter extends StringConverter< RoomType > {

        @Override
        public String toString( RoomType object ) {
            if ( Objects.isNull(object) ) {
                return null;
            }
            return object.translate();
        }

        @Override
        public RoomType fromString( String string ) {
            if ( Objects.isNull(string) ) {
                return null;
            }
            return RoomType.fromTranslation(string);
        }
    }
}






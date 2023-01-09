package fr.univtours.examplanner.entities.dtos;

import fr.univtours.examplanner.controllers.SlotController;
import fr.univtours.examplanner.entities.EditableEntity;
import fr.univtours.examplanner.entities.WithIDEntity;
import fr.univtours.examplanner.exceptions.ControllerException;
import javafx.beans.property.SimpleObjectProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Date;
import java.util.Calendar;
import java.util.Objects;

public class SlotDTO extends WithIDEntity implements EditableEntity {

    /**
     * Date et heure de début du créneau
     */
    private final @NotNull SimpleObjectProperty< Date > day = new SimpleObjectProperty<>();

    private final @NotNull SimpleObjectProperty< Float > hour = new SimpleObjectProperty<>();

    /**
     * Durée du créneau en heures
     */
    private final SimpleObjectProperty< Float > duration = new SimpleObjectProperty<>();

    /**
     * Créneau disponible pour les examens
     *
     * @param id       Identifiant du créneau dans la base de donnée, null si le créneau n'est pas encore enregistré
     * @param start    Date et heure de début du créneau
     * @param duration Durée du créneau en heures
     */
    public SlotDTO( @Nullable String id, @NotNull Date start, float hour, float duration ) {
        super(id);
        this.day.set(start);
        this.hour.set(hour);
        this.duration.set(duration);
    }

    public SimpleObjectProperty< Date > dayProperty() {
        return day;
    }

    public SimpleObjectProperty< Float > hourProperty() {
        return hour;
    }

    public SimpleObjectProperty< Float > durationProperty() {
        return duration;
    }

    public Date getDay() {
        return day.get();
    }

    public void setDay( @NotNull Date day ) {
        this.day.set(day);
    }

    public static float getHourMins( @NotNull Calendar start ) {
        int hour = start.get(Calendar.HOUR);
        float min = start.get(Calendar.MINUTE);
        return hour + min / 100;
    }

    public Float getHour() {
        return hour.get();
    }

    public void setHour( Float hour ) {
        this.hour.set(hour);
    }

    public float getDuration() {
        return duration.get();
    }

    public void setDuration( float duration ) {
        this.duration.set(duration);
    }

    @Override
    public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( null == o || getClass() != o.getClass() ) return false;
        SlotDTO slotDTO = (SlotDTO) o;
        return Objects.equals(id, slotDTO.id);
    }

    @Override
	public int hashCode() {
        return Objects.hash(id, day, hour, duration);
    }

    @Override
    public @NotNull String toString() {
        return "SlotDTO{" +
               "\n\tid: " +
               id +
               ", \n\tday: " +
               day +
               ", \n\thour: " +
               hour +
               ", \n\tduration: " +
               duration +
               "\n}";
    }

    @Override
    public void set( String property, Object value ) throws ControllerException {
        switch ( property ) {
            case "day" -> setDay((Date) value);
            case "hour" -> setHour((Float) value);
            case "duration" -> setDuration((float) value);
            default -> throw new IllegalArgumentException("Unknown property " + property);
        }
        if ( Objects.nonNull(id.get()) ) {
            SlotController.save(this);
        }
    }
}

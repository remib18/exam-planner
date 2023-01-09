package fr.univtours.examplanner.entities.dtos;

import fr.univtours.examplanner.entities.EditableEntity;
import fr.univtours.examplanner.entities.WithIDEntity;
import fr.univtours.examplanner.exceptions.ControllerException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Calendar;
import java.util.Objects;

public class SlotDTO extends WithIDEntity implements EditableEntity {

    /**
     * Date et heure de début du créneau
     */
    private @NotNull Calendar start;

	/**
	 * Durée du créneau en heures
	 */
	private float duration;

    /**
     * Créneau disponible pour les examens
     *
     * @param id       Identifiant du créneau dans la base de donnée, null si le créneau n'est pas encore enregistré
     * @param start    Date et heure de début du créneau
     * @param duration Durée du créneau en heures
     */
    public SlotDTO( @NotNull String id, @Nullable Calendar start, @NotNull Float duration ) {
        super(id);
        this.start = start;
        this.duration = duration;
    }

    public static String getCalendarDate( @NotNull Calendar start ) {
        int year = start.get(Calendar.YEAR);
        int month = start.get(Calendar.MONTH);
        int day = start.get(Calendar.DAY_OF_MONTH);
        String date = year + "-" + month + "-" + day;
        return date;
    }

    public static float getHourMins( @NotNull Calendar start ) {
        int hour = start.get(Calendar.HOUR);
        float min = start.get(Calendar.MINUTE);
        return hour + min / 100;
    }

    public @NotNull Calendar getStart() {
        return start;
    }

    public void setStart( @NotNull Calendar start ) {
        this.start = start;
    }

    public float getDuration() {
        return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}

	@Override
	public boolean equals( Object o ) {
        if ( this == o ) return true;
        if ( null == o || getClass() != o.getClass()) return false;
		SlotDTO slotDTO = (SlotDTO) o;
		return Objects.equals(id, slotDTO.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, start, duration);
	}

	@Override
	public @NotNull String toString() {
		return "SlotDTO{" +
				"\n\tid: " + id +
				", \n\tstart: " + start +
				", \n\tduration: " + duration +
				"\n}";
	}

    @Override
    public void set( String property, Object value ) throws ControllerException {
    }
}

package fr.univtours.examplanner.entities.dtos;

import fr.univtours.examplanner.entities.WithIDEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDateTime;
import java.util.Objects;

public class SlotDTO extends WithIDEntity {

    /**
     * Date et heure de début du créneau
     */
    @NotNull
    private LocalDateTime start;

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
    public SlotDTO(@Nullable String id, @NotNull LocalDateTime start, float duration) {
        super(id);
        this.start = start;
        this.duration = duration;
    }

    public @NotNull LocalDateTime getStart() {
        return start;
    }

    public void setStart(@NotNull LocalDateTime start) {
        this.start = start;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
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
}

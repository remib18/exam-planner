package fr.univtours.examplanner.mappers;

import fr.univtours.examplanner.entities.dtos.SlotDTO;
import fr.univtours.examplanner.exceptions.MappingException;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SlotMapper implements BaseMapper {

    /**
     * Créée une nouvelle classe SlotDTO(id, start, duration) grâce aux éléments de la requête SQL (entities)
     *
     * @param entities = résultats de la requête SQL
     * @return = classe {@link SlotDTO}
     */
    public @NotNull List< SlotDTO > entityToDTO( @NotNull ResultSet entities ) throws MappingException {
        List< SlotDTO > slot = new ArrayList<>();
        try {
            while ( entities.next() ) {
                String id = entities.getString("id");
                String day = entities.getString("day");
                float startHour = entities.getFloat("hour");
                String[] jour = day.split("-");

                int hour = (int) Math.floor(startHour);
                int min = Math.round(( startHour - hour ) * 100);

                Calendar start = Calendar.getInstance();
                start.set(Calendar.MONTH, Integer.parseInt(jour[1]));
                start.set(Calendar.YEAR, Integer.parseInt(jour[0]));
                start.set(Calendar.DAY_OF_MONTH, Integer.parseInt(jour[2]));
                start.set(Calendar.HOUR, hour);
                start.set(Calendar.MINUTE, min);

                float duration = entities.getFloat("duration");
                slot.add(new SlotDTO(id, start, duration));
            }
            return slot;
        } catch ( SQLException e ) {
            throw new MappingException("No entities to map", e);
        }

    }
}
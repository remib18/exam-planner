package fr.univtours.examplanner.mappers;

import fr.univtours.examplanner.entities.dtos.SlotDTO;
import fr.univtours.examplanner.exceptions.MappingException;
import org.jetbrains.annotations.NotNull;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
                Date day = entities.getDate("day");
                float startHour = entities.getFloat("hour");

                float duration = entities.getFloat("duration");
                slot.add(new SlotDTO(id, day, startHour, duration));
            }
            return slot;
        } catch ( SQLException e ) {
            throw new MappingException("No entities to map", e);
        }

    }
}
package fr.univtours.examplanner.mappers;

import fr.univtours.examplanner.entities.dtos.SlotDTO;
import org.jetbrains.annotations.NotNull;
import java.sql.PreparedStatement
import java.sql.ResultSet;

public class SlotMapper implements BaseMapper{

    /**
     * Créée une nouvelle classe SlotDTO(id, start, duration)
     * grâce aux éléments de la requête SQL (entities)
     *
     * @param entities = résultats de la requête SQL
     * @return = classe {@link SlotDTO}
     */
    public @NotNull SlotDTO EntityToDTO(@NotNull ResultSet entities) {
        // TODO implement here
        throw new UnsupportedOperationException();
    }

    /**
     * Permet l'utilisation des attributs de la classe SlotDTO(id, start, duration)
     * dans une requête SQL
     *
     * @param dto = attributs de la classe {@link SlotDTO}
     * @return = éléments nécessaires à une requête SQL
     */
    public @NotNull String[] DTOToEntity( @NotNull SlotDTO dto) {
        // TODO implement here
        throw new UnsupportedOperationException();
    }

}
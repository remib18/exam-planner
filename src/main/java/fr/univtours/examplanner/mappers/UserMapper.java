package fr.univtours.examplanner.mappers;

import fr.univtours.examplanner.entities.dtos.UserDTO;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;

public class UserMapper implements BaseMapper{

    /**
     * Créée une nouvelle classe UserDTO(id, mail, department, role)
     * grâce aux éléments de la requête SQL (entities)
     *
     * @param entities = résultats de la requête SQL
     * @return = classe {@link UserDTO}
     */
    public @NotNull UserDTO EntityToDTO(@NotNull ResultSet entities) {
        // TODO implement here
        throw new UnsupportedOperationException();
    }

    /**
     * Permet l'utilisation des attributs de la classe SlotDTO(id, start, duration)
     * dans une requête SQL
     *
     * @param dto = attributs de la classe {@link UserDTO}
     * @return = éléments nécessaires à une requête SQL
     */
    public @NotNull PreparedStatement DTOToEntity( @NotNull UserDTO dto) {
        // TODO implement here
        throw new UnsupportedOperationException();
    }

}
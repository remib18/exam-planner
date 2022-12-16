package fr.univtours.examplanner.mappers;

import fr.univtours.examplanner.entities.dtos.ManagerDTO;
import org.jetbrains.annotations.NotNull;
import java.sql.PreparedStatement
import java.sql.ResultSet;

public class ManagerMapper implements BaseMapper{

    /**
     * Créée une nouvelle classe ManagerDTO(id, civility, lastName, firstName)
     * grâce aux éléments de la requête SQL (entities)
     *
     * @param entities = résultats de la requête SQL
     * @return = classe {@link ManagerDTO}
     */
    public @NotNull ManagerDTO EntityToDTO(@NotNull ResultSet entities) {
        // TODO implement here
        throw new UnsupportedOperationException();
    }

    /**
     * Permet l'utilisation des attributs de la classe ManagerDTO(id, civility, lastName, firstName)
     * dans une requête SQL
     *
     * @param dto = attributs de la classe {@link ManagerDTO}
     * @return = éléments nécessaires à une requête SQL
     */
    public @NotNull PreparedStatement DTOToEntity( @NotNull ManagerDTO dto) {
        // TODO implement here
        throw new UnsupportedOperationException();
    }

}
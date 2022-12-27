package fr.univtours.examplanner.mappers;

import fr.univtours.examplanner.entities.dtos.GroupDTO;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GroupMapper implements BaseMapper{

    /**
     * Créée une nouvelle classe GroupDTO(id, name,
     *      containsStudentsWithReducedMobility,
     *      numberOfStudentsWithWriterNeeds,
     *      numberOfStudentsWithIsolationNeeds,
     *      numberOfStudentsWithPartTimeNeeds,
     *      numberOfStudents,
     *      childrenIds)
     * grâce aux éléments de la requête SQL (entity)
     *
     * @param entities  = résultats de la requête SQL
     * @return = classe {@link GroupDTO}
     */
    public @NotNull GroupDTO EntityToDTO(@NotNull ResultSet entities) {
        // TODO implement here
        throw new UnsupportedOperationException();
    }

    /**
     * Permet l'utilisation des attributs de la classe GroupDTO(id, name,
     *      containsStudentsWithReducedMobility,
     *      numberOfStudentsWithWriterNeeds,
     *      numberOfStudentsWithIsolationNeeds,
     *      numberOfStudentsWithPartTimeNeeds,
     *      numberOfStudents,
     *      childrenIds)
     *      dans une requête SQL
     *
     * @param dto = attributs de la classe {@link GroupDTO}
     * @return = éléments nécessaires à une requête SQL
     */
    public @NotNull PreparedStatement DTOToEntity( @NotNull GroupDTO dto) {
        // TODO implement here
        throw new UnsupportedOperationException();
    }

}
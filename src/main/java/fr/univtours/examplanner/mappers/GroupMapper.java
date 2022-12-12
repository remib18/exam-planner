package fr.univtours.examplanner.mappers;

import fr.univtours.examplanner.entities.dtos.GroupDTO;
import org.jetbrains.annotations.NotNull;

public class GroupMapper implements BaseMapper{

    // Todo(@gab): Remplacer Object avec retour de Benoit (repo)

    /**
     * Créée une nouvelle classe GroupDTO(id, name,
     *      containsStudentsWithReducedMobility,
     *      numberOfStudentsWithWriterNeeds,
     *      numberOfStudentsWithIsolationNeeds,
     *      numberOfStudentsWithPartTimeNeeds,
     *      numberOfStudents,
     *      childrenIds)
     *
     * grâce aux éléments de la requête SQL (entity)
     * @param entity  = résultats de la requête SQL
     * @return = classe {@link GroupDTO}
     */
    public @NotNull GroupDTO EntityToDTO(@NotNull Object entity) {
        // TODO implement here
        throw new UnsupportedOperationException();
    }

    // Todo(@gab): same

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
    public @NotNull Object DTOToEntity(@NotNull GroupDTO dto) {
        // TODO implement here
        throw new UnsupportedOperationException();
    }

}
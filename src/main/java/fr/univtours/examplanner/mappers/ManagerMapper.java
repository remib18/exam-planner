package fr.univtours.examplanner.mappers;

import fr.univtours.examplanner.entities.dtos.ManagerDTO;
import org.jetbrains.annotations.NotNull;

public class ManagerMapper implements BaseMapper{

    // Todo(@gab): Remplacer Object avec retour de Benoit (repo)

    /**
     * Créée une nouvelle classe ManagerDTO(id, civility, lastName, firstName)
     * grâce aux éléments de la requête SQL (entity)
     *
     * @param entity = résultats de la requête SQL
     * @return = classe {@link ManagerDTO}
     */
    public @NotNull ManagerDTO EntityToDTO(@NotNull Object entity) {
        // TODO implement here
        throw new UnsupportedOperationException();
    }

    // Todo(@gab): same

    /**
     * Permet l'utilisation des attributs de la classe ManagerDTO(id, civility, lastName, firstName)
     * dans une requête SQL
     *
     * @param dto = attributs de la classe {@link ManagerDTO}
     * @return = éléments nécessaires à une requête SQL
     */
    public @NotNull Object DTOToEntity(@NotNull ManagerDTO dto) {
        // TODO implement here
        throw new UnsupportedOperationException();
    }

}
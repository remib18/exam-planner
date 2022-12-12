package fr.univtours.examplanner.mappers;

import fr.univtours.examplanner.entities.dtos.UserDTO;
import org.jetbrains.annotations.NotNull;

public class UserMapper implements BaseMapper{

    // Todo(@gab): Remplacer Object avec retour de Benoit (repo)

    /**
     * Créée une nouvelle classe UserDTO(id, mail, department, role)
     * grâce aux éléments de la requête SQL (entity)
     *
     * @param entity = résultats de la requête SQL
     * @return = classe {@link UserDTO}
     */
    public @NotNull UserDTO EntityToCommonDTO(@NotNull Object entity) {
        // TODO implement here
        throw new UnsupportedOperationException();
    }

    // Todo(@gab): Remplacer Object avec retour de Benoit (repo)

    /**
     * Permet l'utilisation des attributs de la classe SlotDTO(id, start, duration)
     * dans une requête SQL
     *
     * @param dto = attributs de la classe {@link UserDTO}
     * @return = éléments nécessaires à une requête SQL
     */
    public @NotNull Object CommonDTOToEntity(@NotNull UserDTO dto) {
        // TODO implement here
        throw new UnsupportedOperationException();
    }

}
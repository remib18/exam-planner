package fr.univtours.examplanner.mappers;

import fr.univtours.examplanner.entities.dtos.SlotDTO;
import org.jetbrains.annotations.NotNull;

public class SlotMapper implements BaseMapper{

    // Todo(@gab): Remplacer Object avec retour de Benoit (repo)

    /**
     * Créée une nouvelle classe SlotDTO(id, start, duration)
     * grâce aux éléments de la requête SQL (entity)
     *
     * @param entity = résultats de la requête SQL
     * @return = classe {@link SlotDTO}
     */
    public @NotNull SlotDTO EntityToDTO(@NotNull Object entity) {
        // TODO implement here
        throw new UnsupportedOperationException();
    }

    // Todo(@gab): same

    /**
     * Permet l'utilisation des attributs de la classe SlotDTO(id, start, duration)
     * dans une requête SQL
     *
     * @param dto = attributs de la classe {@link SlotDTO}
     * @return = éléments nécessaires à une requête SQL
     */
    public @NotNull Object DTOToEntity(@NotNull SlotDTO dto) {
        // TODO implement here
        throw new UnsupportedOperationException();
    }

}
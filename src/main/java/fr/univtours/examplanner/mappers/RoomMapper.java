package fr.univtours.examplanner.mappers;

import fr.univtours.examplanner.entities.dtos.RoomDTO;
import org.jetbrains.annotations.NotNull;

public class RoomMapper implements BaseMapper{

    /**
     * Créée une nouvelle classe RoomDTO(name, place, type, computerEnvironments, equipments, availableSlots)
     * grâce aux éléments de la requête SQL (entities)
     *
     * @param entities = résultats de la requête SQL
     * @return = classe {@link RoomDTO}
     */
    public @NotNull RoomDTO EntityToDTO(@NotNull Object entities) {
        // TODO implement here
        throw new UnsupportedOperationException();
    }

    /**
     * Permet l'utilisation des attributs de la classe RoomDTO(name, place, type, computerEnvironments, equipments, availableSlots)
     * dans une requête SQL
     *
     * @param dto = attributs de la classe {@link RoomDTO}
     * @return = éléments nécessaires à une requête SQL
     */
    public @NotNull Object DTOToEntity(@NotNull RoomDTO dto) {
        // TODO implement here
        throw new UnsupportedOperationException();
    }

}
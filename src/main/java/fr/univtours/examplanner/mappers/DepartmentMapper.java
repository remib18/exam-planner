package fr.univtours.examplanner.mappers;

import fr.univtours.examplanner.entities.dtos.DepartmentDTO;
import org.jetbrains.annotations.NotNull;

public class DepartmentMapper implements BaseMapper {

    /**
     * Créée une nouvelle classe DepartmentDTO(id, name, MockUpDTOs)
     * grâce aux éléments de la requête SQL (entities)
     *
     * @param entities = résultats de la requête SQL
     * @return = classe {@link DepartmentDTO}
     */
    public static @NotNull DepartmentDTO EntityToDTO( @NotNull Object entities) {
        // TODO implement here
        throw new UnsupportedOperationException();
    }

    /**
     * Permet l'utilisation des attributs de la classe DepartmentDTO(id, name, MockUpDTOs)
     * dans une requête SQL
     *
     * @param dto = attributs de la classe {@link DepartmentDTO}
     * @return = éléments nécessaires à une requête SQL
     */
    public static @NotNull Object DTOToEntity(@NotNull DepartmentDTO dto) {
        // TODO implement here
        throw new UnsupportedOperationException() ;
    }

}
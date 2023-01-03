package fr.univtours.examplanner.mappers;

import fr.univtours.examplanner.entities.dtos.DepartmentDTO;
import fr.univtours.examplanner.exceptions.MappingException;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentMapper implements BaseMapper {
    /**
     * Créée une nouvelle classe DepartmentDTO(name, MockUpDTOs)
     * grâce aux éléments de la requête SQL (entities)
     *
     * @param entities = résultats de la requête SQL
     * @return = classe {@link DepartmentDTO}
     */
    public static @NotNull List < DepartmentDTO > EntityToTDO( ResultSet entities ) throws MappingException,
            SQLException {
        List < DepartmentDTO > departments = new ArrayList<>();
            if (entities.next()) {
                String name = entities.getString("name");
                DepartmentDTO department = new DepartmentDTO(name);
                departments.add(department);
                return departments;
            }
            throw  new MappingException("No result to map.", null);
    }
}
package fr.univtours.examplanner.mappers;

import fr.univtours.examplanner.entities.dtos.SubjectDTO;
import fr.univtours.examplanner.exceptions.MappingException;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectMapper implements BaseMapper {

    /**
     * Créée une nouvelle classe SubjectDTO(id, nom) grâce aux éléments de la requête SQL (entities)
     *
     * @param entities Résultats de la requête SQL
     * @return Classe SubjectDTO
     *
     * @throws MappingException
     */
    public @NotNull List< SubjectDTO > entityToDTO( @NotNull ResultSet entities ) throws MappingException {
        List< SubjectDTO > subjects = new ArrayList<>();
        try {
            while ( entities.next() ) {
                String id = entities.getString("id");
                String name = entities.getString("name");
                subjects.add(new SubjectDTO(id, name));
            }
            return subjects;
        } catch ( SQLException e ) {
            throw new MappingException("Unable to map entity", e);
        }
    }

}

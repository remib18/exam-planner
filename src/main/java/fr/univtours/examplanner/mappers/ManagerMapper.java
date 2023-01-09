package fr.univtours.examplanner.mappers;

import fr.univtours.examplanner.entities.dtos.ManagerDTO;
import fr.univtours.examplanner.enums.Civility;
import fr.univtours.examplanner.exceptions.MappingException;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ManagerMapper implements BaseMapper{

    /**
     * Créée une nouvelle classe ManagerDTO(id, civility, lastName, firstName) grâce aux éléments de la requête SQL
     * (entities)
     *
     * @param entities = résultats de la requête SQL
     * @return = classe {@link ManagerDTO}
     */
    @Override
    public @NotNull List< ManagerDTO > entityToDTO( @NotNull ResultSet entities ) throws MappingException {
        List < ManagerDTO > managers = new ArrayList<>();
        try {
            while ( entities.next() ) {
                String id = entities.getString("id");
                Civility civility = Civility.parse("civility");
                String lastname = entities.getString("lastname");
                String firstname = entities.getString("firstname");
                managers.add(new ManagerDTO(id, civility, lastname, firstname));
            }
            return managers;
        } catch ( SQLException | ParseException e ) {
            throw new MappingException("Unable to map entity", e);
        }
    }

}
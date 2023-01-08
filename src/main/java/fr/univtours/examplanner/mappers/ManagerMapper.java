package fr.univtours.examplanner.mappers;

import fr.univtours.examplanner.entities.dtos.ManagerDTO;
import fr.univtours.examplanner.enums.Civility;
import fr.univtours.examplanner.exceptions.MappingException;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

public class ManagerMapper implements BaseMapper{

    /**
     * Créée une nouvelle classe ManagerDTO(id, civility, lastName, firstName) grâce aux éléments de la requête SQL
     * (entities)
     *
     * @param entities = résultats de la requête SQL
     * @return = classe {@link ManagerDTO}
     */
    public @NotNull ManagerDTO EntityToDTO( @NotNull ResultSet entities ) throws MappingException {
        ManagerDTO manager = new ManagerDTO(null, null, null, null);
        try {
            while ( entities.next() ) {
                String id = entities.getString("id");
                Civility civility = Civility.parse("civility");
                String lastname = entities.getString("lastname");
                String firstname = entities.getString("firstname");
                manager = new ManagerDTO(id, civility, lastname, firstname);
            }
            return manager;
        } catch ( SQLException | ParseException e ) {
            throw new MappingException("Unable to map entity", e);
        }
    }

}
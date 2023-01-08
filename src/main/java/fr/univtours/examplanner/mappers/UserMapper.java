package fr.univtours.examplanner.mappers;

import fr.univtours.examplanner.entities.dtos.UserDTO;
import fr.univtours.examplanner.exceptions.MappingException;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserMapper implements BaseMapper{

    /**
     * Créée une nouvelle classe UserDTO(id, mail, department, role) grâce aux éléments de la requête SQL (entities)
     *
     * @param entities = résultats de la requête SQL
     * @return = classe {@link UserDTO}
     */
    public static @NotNull List< UserDTO > entityToDTO( @NotNull ResultSet entities ) throws MappingException {
        List< UserDTO > users = new ArrayList<>();
        try {
            while ( entities.next() ) {
                String id = entities.getString("id");
                String mail = entities.getString("mail");
                String encryptedPassword = entities.getString("password");
                String departmentId = entities.getString("department");
                String managerId = entities.getString("manager");
                users.add(new UserDTO(id, mail, encryptedPassword, departmentId, managerId));
            }
            return users;
        } catch ( SQLException e ) {
            throw new MappingException("Unable to map entity.", e);
        }
    }

}
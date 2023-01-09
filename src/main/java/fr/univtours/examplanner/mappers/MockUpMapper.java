package fr.univtours.examplanner.mappers;

import fr.univtours.examplanner.entities.dtos.MockUpDTO;
import fr.univtours.examplanner.enums.Degree;
import fr.univtours.examplanner.exceptions.MappingException;
import fr.univtours.examplanner.utils.Database;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class MockUpMapper implements BaseMapper {

    public static @NotNull List< MockUpDTO > entityToDTO( @NotNull ResultSet entities ) throws MappingException {
        List< MockUpDTO > mockup = new ArrayList<>();
        try {
            while ( entities.next() ) {
                String id = entities.getString("id");
                String name = entities.getString("name");
                Degree degree = Degree.parse(entities.getString("degree"));
                int semester = entities.getInt("semester");
                List< String > subjects = Database.mysqlSetToList(entities.getString("subjects"));
                mockup.add(new MockUpDTO(id, name, degree, semester, subjects));
            }
            return mockup;
        } catch ( SQLException | ParseException e ) {
            throw new MappingException("Unable to map entity", e);
        }
    }

}

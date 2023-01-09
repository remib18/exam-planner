package fr.univtours.examplanner.mappers;

import fr.univtours.examplanner.entities.dtos.GroupDTO;
import fr.univtours.examplanner.exceptions.MappingException;
import fr.univtours.examplanner.exceptions.RepoException;
import fr.univtours.examplanner.repositories.GroupRepo;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupMapper implements BaseMapper{

    /**
     * Créée une nouvelle classe GroupDTO(id, name,
     *      containsStudentsWithReducedMobility,
     *      numberOfStudentsWithWriterNeeds,
     *      numberOfStudentsWithIsolationNeeds,
     *      numberOfStudentsWithPartTimeNeeds,
     *      numberOfStudents,
     *      childrenIds)
     * grâce aux éléments de la requête SQL (entity)
     *
     * @param entities  = résultats de la requête SQL
     * @return = classe {@link GroupDTO}
     */
    public @NotNull List< GroupDTO > entityToDTO( @NotNull ResultSet entities ) throws MappingException {
        List< GroupDTO > group = new ArrayList<>();
        try {
            while ( entities.next() ) {
                String id = entities.getString("id");
                String name = entities.getString("name");
                boolean cRMP = entities.getBoolean("containReducedMobilityPerson");
                int nOSWA = entities.getInt("numberOfStudentsWithoutAdjustment");
                int nOSWWN = entities.getInt("numberOfStudentsWithWritingNeeds");
                int nOSWIR = entities.getInt("numberOfStudentsWithIsolatedRooms");
                int nOSWPT = entities.getInt("numberOfStudentsWithPartTime");
                List< String > child = ( new GroupRepo().getAllChildFromGroup(id) );
                group.add(new GroupDTO(id, name, cRMP, nOSWA, nOSWWN, nOSWIR, nOSWPT, child));
            }
            return group;
        } catch ( SQLException | RepoException e ) {
            throw new MappingException("Unable to map entity.", e);
        }
    }

}
package fr.univtours.examplanner.repositories;

import fr.univtours.examplanner.entities.dtos.ExamDTO;
import fr.univtours.examplanner.entities.dtos.GroupDTO;
import fr.univtours.examplanner.exceptions.DatabaseConnectionException;
import fr.univtours.examplanner.exceptions.RepoException;
import fr.univtours.examplanner.utils.Database;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GroupRepo implements BaseRepo<GroupDTO, String> {

    ArrayList< String > groupIDs;

    /**
     * Récupère tous les examens
     *
     * @param e un examen
     * @return la liste des examens correspondants
     */
    public @NotNull List< GroupDTO > getAllFromExam( ExamDTO e ) throws RepoException {
        String idE = e.getId();
        String sql = "SELECT group FROM _ExamToGroup WHERE exam = ?";
        List< GroupDTO > result = new ArrayList<>();
        try ( PreparedStatement stm = Database.getConnection().prepareStatement(sql) ) {
            stm.setString(1, idE);
            ResultSet res = stm.executeQuery();
            while ( res.next() ) {
                String id = res.getString("id");
                String name = res.getString("name");
                boolean cRMP = res.getBoolean("containReducedMobilityPerson");
                int nOSWA = res.getInt("numberOfStudentsWithoutAdjustment");
                int nOSWWN = res.getInt("numberOfStudentsWithWritingNeeds");
                int nOSWIR = res.getInt("numberOfStudentsWithIsolatedRooms");
                int nOSWPT = res.getInt("numberOfStudentsWithPartTime");
                List< String > child = ( new GroupRepo().getAllChildFromGroup(id) );
                result.add(new GroupDTO(id, name, cRMP, nOSWA, nOSWWN, nOSWIR, nOSWPT, child));
            }
            return result;
        } catch ( SQLException | DatabaseConnectionException ex ) {
            throw new RepoException("Getting group failed, no rows affected", ex);

        }

    }

    /**
     * Récupère tous les sous-groupes du groupe passé en paramètre
     *
     * @param id identifiant d'un groupe
     * @return la liste des groupes correspondants
     */
    public @NotNull List< String > getAllChildFromGroup( @NotNull String id ) throws RepoException {
        String sql = "SELECT * FROM _GroupToGroup WHERE parent = ?";
        List< String > result = new ArrayList<>();
        try ( PreparedStatement stm = Database.getConnection().prepareStatement(sql) ) {
            stm.setString(1, id);
            ResultSet res = stm.executeQuery();
            while ( res.next() ) {
                result.add(res.getString("child"));
            }
            return result;
        } catch ( SQLException | DatabaseConnectionException e ) {
            throw new RepoException("Getting group failed, no rows affected", e);
        }
    }

    @Override
    public @NotNull GroupDTO save( @NotNull GroupDTO entity ) throws RepoException {
        boolean hasId = !Objects.isNull(entity.getId());
        String id = hasId ? entity.getId() : Database.getNewUUID();
        String sql;
        if ( hasId ) {
            sql = "INSERT INTO Group (id, name, containReducedMobilityPerson, numberOfStudentsWithoutAdjustment, " +
                  "numberOfStudentsWithWritingNeeds, numberOfStudentsWithIsolatedRooms, numberOfStudentsWithPartTime)" +
                  " " +
                  "VALUES (" +
                  id +
                  ", ?, ?, ?, ?, ?, ?)";
        } else {
            sql = "UPDATE Group SET nom= ?, " +
                  "containReducedMobilityPerson = ?, " +
                  "numberOfStudentsWithoutAdjustment = ?, " +
                  "numberOfStudentsWithWritingNeeds = ?, " +
                  "numberOfStudentsWithIsolatedRooms = ?, " +
                  "numberOfStudentsWithPartTime = ? " +
                  "WHERE id = " +
                  id;
        }
        try ( PreparedStatement stm = Database.getConnection().prepareStatement(sql) ) {
            stm.setString(1, entity.getName());
            stm.setBoolean(2, entity.doesContainsStudentsWithReducedMobility());
            stm.setInt(3, entity.getNumberOfStudentsWithoutAdjustment());
            stm.setInt(4, entity.getNumberOfStudentsWithWriterNeeds());
            stm.setInt(5, entity.getNumberOfStudentsWithIsolationNeeds());
            stm.setInt(6, entity.getNumberOfStudentsWithPartTimeNeeds());
            int rows = stm.executeUpdate();
            if ( 0 == rows ) {
                throw new RepoException("Creating group failed, no rows affected", null);
            }
            entity.setId(id);
            return entity;
        } catch ( SQLException | DatabaseConnectionException e ) {
            throw new RepoException("Creating group failed, no rows affected", e);
        }
    }


    @Override
    public @NotNull List< GroupDTO > getAll() throws RepoException {
        String sql = "SELECT * FROM `Group`";
        List< GroupDTO > result = new ArrayList<>();
        try ( PreparedStatement stm = Database.getConnection().prepareStatement(sql) ) {
            ResultSet res = stm.executeQuery();
            while ( res.next() ) {
                String id = res.getString("id");
                String name = res.getString("name");
                boolean cRMP = res.getBoolean("containReducedMobilityPerson");
                int nOSWA = res.getInt("numberOfStudentsWithoutAdjustment");
                int nOSWWN = res.getInt("numberOfStudentsWithWritingNeeds");
                int nOSWIR = res.getInt("numberOfStudentsWithIsolatedRooms");
                int nOSWPT = res.getInt("numberOfStudentsWithPartTime");
                List< String > child = ( new GroupRepo().getAllChildFromGroup(id) );
                result.add(new GroupDTO(id, name, cRMP, nOSWA, nOSWWN, nOSWIR, nOSWPT, child));
            }
            return result;
        } catch ( SQLException | DatabaseConnectionException e ) {
            throw new RepoException("Getting group failed, no rows affected", e);
        }
    }

    @Override
    public @NotNull GroupDTO getById( @NotNull String id ) throws RepoException {
        String sql = "SELECT * FROM `Group` WHERE id = ?";
        try ( PreparedStatement stm = Database.getConnection().prepareStatement(sql) ) {
            stm.setString(1, id);
            stm.execute(sql);
            ResultSet res = stm.executeQuery();
            GroupDTO group = new GroupDTO(null, null, false, 0, 0, 0, 0, null);
            while ( res.next() ) {
                String idG = res.getString("id");
                String name = res.getString("name");
                boolean cRMP = res.getBoolean("containReducedMobilityPerson");
                int nOSWA = res.getInt("numberOfStudentsWithoutAdjustment");
                int nOSWWN = res.getInt("numberOfStudentsWithWritingNeeds");
                int nOSWIR = res.getInt("numberOfStudentsWithIsolatedRooms");
                int nOSWPT = res.getInt("numberOfStudentsWithPartTime");
                List< String > child = ( new GroupRepo().getAllChildFromGroup(id) );
                group = new GroupDTO(idG, name, cRMP, nOSWA, nOSWWN, nOSWIR, nOSWPT, child);
            }
            return group;
        } catch ( SQLException | DatabaseConnectionException e ) {
            throw new RepoException("Getting group failed, no rows affected", e);
        }

    }

    @Override
    public boolean delete( @NotNull GroupDTO entity ) throws RepoException {
        String id = entity.getId();
        String sql = "DELETE FROM `Group` WHERE id = ?";
        try ( PreparedStatement stm = Database.getConnection().prepareStatement(sql) ) {
            stm.setString(1, id);
            stm.execute(sql);
        } catch ( SQLException | DatabaseConnectionException e ) {
            throw new RepoException("Deleting group failed, no rows affected", e);
        }
        return true;
    }

}
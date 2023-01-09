package fr.univtours.examplanner.repositories;

import fr.univtours.examplanner.entities.dtos.ExamDTO;
import fr.univtours.examplanner.entities.dtos.ManagerDTO;
import fr.univtours.examplanner.enums.Civility;
import fr.univtours.examplanner.exceptions.DatabaseConnectionException;
import fr.univtours.examplanner.exceptions.RepoException;
import fr.univtours.examplanner.utils.Database;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ManagerRepo implements BaseRepo< ManagerDTO, String > {

    /**
     * Récupère tous les examens
     *
     * @param e un examen
     * @return la liste des managers correspondants à l'examen
     */
    public @NotNull List< ManagerDTO > getAllFromExam( @NotNull ExamDTO e ) throws RepoException {
        String idE = e.getId();
        String sql = "SELECT manager FROM _ExamToManager WHERE exam = ?";
        List< ManagerDTO > result = new ArrayList<>();
        try ( PreparedStatement stm = Database.getConnection().prepareStatement(sql) ) {
            stm.setString(1, idE);
            ResultSet res = stm.executeQuery();
            while ( res.next() ) {
                String id = res.getString("id");
                Civility civility = Civility.parse("civility");
                String lastname = res.getString("lastname");
                String firstname = res.getString("firstname");
                result.add(new ManagerDTO(id, civility, lastname, firstname));
            }
            return result;
        } catch ( SQLException | DatabaseConnectionException | ParseException ex ) {
            throw new RepoException("Getting manager failed, no rows affected", ex);
        }
    }

    @Override
    public @NotNull ManagerDTO save( @NotNull ManagerDTO entity ) throws RepoException {
        boolean hasId = !Objects.isNull(entity.getId());
        String id = hasId ? entity.getId() : Database.getNewUUID();
        String sql;
        if ( hasId ) {
            sql = "INSERT INTO Manager (id, civility, lastname, firstname) VALUES ('" + id + "',?, ?, ?)";
        } else {
            sql = "UPDATE Manager SET civility = ?, lastname = ?, firstname= ? WHERE id = '" + id + "'";
        }
        try ( PreparedStatement stm = Database.getConnection().prepareStatement(sql) ) {
            stm.setString(1, entity.getCivility().name());
            stm.setString(2, entity.getLastName());
            stm.setString(3, entity.getFirstName());
            int rows = stm.executeUpdate();
            if ( 0 == rows ) {
                throw new RepoException("Creating manager failed, no rows affected", null);
            }
            entity.setId(id);
            return entity;

        } catch ( SQLException | DatabaseConnectionException e ) {
            throw new RepoException("Creating manager failed, no rows affected", e);
        }

    }

    @Override
    public @NotNull List< ManagerDTO > getAll() throws RepoException {
        String sql = "SELECT * FROM Manager";
        List< ManagerDTO > result = new ArrayList<>();
        try ( PreparedStatement stm = Database.getConnection().prepareStatement(sql) ) {
            ResultSet res = stm.executeQuery();
            while ( res.next() ) {
                String id = res.getString("id");
                Civility civility = Civility.parse("civility");
                String lastname = res.getString("lastname");
                String firstname = res.getString("firstname");
            }
        } catch ( SQLException | DatabaseConnectionException | ParseException e ) {
            throw new RepoException("Getting manager failed, no rows affected", e);
        }

        return new ArrayList<>();
    }

    public @NotNull ManagerDTO getByFullName( String lastname, String firstname ) throws RepoException {
        String sql = "SELECT * FROM Manager WHERE lastname = ? AND firstname = ?";
        try ( PreparedStatement stm = Database.getConnection().prepareStatement(sql) ) {
            stm.setString(1, lastname);
            stm.setString(2, firstname);
            ResultSet res = stm.executeQuery();
            ManagerDTO manager = new ManagerDTO(null, null, lastname, firstname);
            while ( res.next() ) {
                String idM = res.getString("id");
                Civility civility = Civility.parse("civility");
                manager = new ManagerDTO(idM, civility, lastname, firstname);
            }
            return manager;
        } catch ( SQLException | DatabaseConnectionException | ParseException e ) {
            throw new RepoException("Getting manager failed, no rows affected", e);
        }
    }

    @Override
    public @NotNull ManagerDTO getById( @NotNull String id ) throws RepoException {
        String sql = "SELECT * FROM Manager WHERE id = ?";
        try ( PreparedStatement stm = Database.getConnection().prepareStatement(sql) ) {
            stm.setString(1, id);
            stm.execute(sql);
            ResultSet res = stm.executeQuery();
            ManagerDTO manager = new ManagerDTO(null, null, null, null);
            while ( res.next() ) {
                String idM = res.getString("id");
                Civility civility = Civility.parse("civility");
                String lastname = res.getString("lastname");
                String firstname = res.getString("firstname");
                manager = new ManagerDTO(idM, civility, lastname, firstname);
            }
            return manager;
        } catch ( SQLException | DatabaseConnectionException | ParseException e ) {
            throw new RepoException("Getting manager failed, no rows affected", e);
        }
    }

    @Override
    public boolean delete( @NotNull ManagerDTO entity ) throws RepoException {
        String id = entity.getId();
        String sql = "DELETE FROM Manager WHERE id = ?";
        try ( PreparedStatement stm = Database.getConnection().prepareStatement(sql) ) {
            stm.setString(1, id);
            stm.execute(sql);
        } catch ( SQLException | DatabaseConnectionException e ) {
            throw new RepoException("Deleting manager failed, no rows affected", e);
        }
        return true;
    }
}
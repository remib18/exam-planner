package fr.univtours.examplanner.repositories;

import fr.univtours.examplanner.entities.dtos.RoomDTO;
import fr.univtours.examplanner.exceptions.DatabaseConnectionException;
import fr.univtours.examplanner.exceptions.MappingException;
import fr.univtours.examplanner.exceptions.RepoException;
import fr.univtours.examplanner.mappers.RoomMapper;
import fr.univtours.examplanner.utils.Database;
import fr.univtours.examplanner.utils.EntityUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class RoomRepo implements BaseRepo< RoomDTO, String > {


    @Override
    public @NotNull List< RoomDTO > getAll() throws RepoException {
        return getAllFrom(null, null);
    }

    private @NotNull List< RoomDTO > getAllFrom( @Nullable String key, @Nullable String value ) throws RepoException {
        String sql = "SELECT * FROM room";
        boolean withOptions = !Objects.isNull(key) && !Objects.isNull(value);
        if ( withOptions ) {
            sql += " WHERE " + key + " = ?";
        }
        try ( PreparedStatement stm = Database.getConnection().prepareStatement(sql) ) {
            if ( withOptions ) {
                stm.setString(1, value);
            }
            return RoomMapper.EntityToDTO(stm.executeQuery());
        } catch ( SQLException | DatabaseConnectionException | MappingException e ) {
            throw new RepoException("Getting rooms failed, no rows affected.", e);
        }
    }

    public @NotNull RoomDTO getById( @NotNull String name ) throws RepoException {
        List< RoomDTO > res = getAllFrom("name", name);
        if ( res.isEmpty() ) {
            return null;
        }
        return res.get(0);
    }

    public @NotNull RoomDTO getByPlaces( @NotNull int places ) throws RepoException {
        List< RoomDTO > res = getAllFrom("places", String.valueOf(places));
        if ( res.isEmpty() ) {
            return null;
        }
        return res.get(0);
    }

    public @NotNull List< RoomDTO > getByType( @NotNull String type ) throws RepoException {
        String sql = "SELECT * FROM room WHERE FIND_IN_SET(?, types) > 0";
        try ( PreparedStatement stm = Database.getConnection().prepareStatement(sql) ) {
            stm.setString(1, type);
            stm.execute(sql);
            return RoomMapper.EntityToDTO(stm.executeQuery());
        } catch ( DatabaseConnectionException | SQLException | MappingException e ) {
            throw new RepoException("Fail", e);
        }
    }

    public @NotNull List< RoomDTO > getByCE( @NotNull String CE ) throws RepoException {
        String sql = "SELECT * FROM room WHERE FIND_IN_SET(?, computerEnvironment) > 0";
        try ( PreparedStatement stm = Database.getConnection().prepareStatement(sql) ) {
            stm.setString(1, CE);
            stm.execute(sql);
            return RoomMapper.EntityToDTO(stm.executeQuery());
        } catch ( DatabaseConnectionException | SQLException | MappingException e ) {
            throw new RepoException("Fail", e);
        }
    }

    public @NotNull List< RoomDTO > getByRE( @NotNull String Equipments ) throws RepoException {
        String sql = "SELECT * FROM room WHERE FIND_IN_SET(?, equipments) > 0";
        try ( PreparedStatement stm = Database.getConnection().prepareStatement(sql) ) {
            stm.setString(1, Equipments);
            stm.execute(sql);
            return RoomMapper.EntityToDTO(stm.executeQuery());
        } catch ( DatabaseConnectionException | SQLException | MappingException e ) {
            throw new RepoException("Fail", e);
        }
    }

    public @NotNull List< RoomDTO > getByAS( @NotNull String name ) throws RepoException {
        String sql = "SELECT * FROM _slottoroom WHERE room = ?";
        try ( PreparedStatement stm = Database.getConnection().prepareStatement(sql) ) {
            stm.setString(1, name);
            return RoomMapper.EntityToDTO(stm.executeQuery());

        } catch ( DatabaseConnectionException | SQLException | MappingException e ) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public @NotNull RoomDTO save( @NotNull RoomDTO entity )
    throws SQLException, DatabaseConnectionException, RepoException {
        boolean hasId = !Objects.isNull(entity.getName());
        String id = hasId ? entity.getName() : Database.getNewUUID();
        String sql;
        if ( hasId ) {
            sql = "INSERT INTO room (name, places, types, equipments, computerEnvironments) VALUES ( id ," +
                  "?, ?," +
                  " ?, ?)";
        } else {
            sql = "UPDATE room SET places = ?, types = ?, equipments = ?, computerEnvironments = ? WHERE name = " +
                  entity.getName();
        }
        try ( PreparedStatement pstmt = Database.getConnection().prepareStatement(sql) ) {
            pstmt.setInt(1, entity.getPlaces());
            pstmt.setString(2, entity.getType().toString());

            String CE = EntityUtils.listToString(entity.getComputerEnvironments(), el -> el.toString());
            CE = CE.substring(1, CE.length() - 1);
            pstmt.setString(3, CE);

            String equipments = EntityUtils.listToString(entity.getEquipments(), el -> el.toString());
            equipments = equipments.substring(1, equipments.length() - 1);
            pstmt.setString(4, equipments);

            int rows = pstmt.executeUpdate();
            if ( 0 == rows ) {
                throw new RepoException("Creating room failed, no rows affected", null);
            }
            entity.setName(entity.getName());
            return entity;
        } catch ( DatabaseConnectionException | SQLException e ) {
            throw new RepoException("Creating room failed, no rows affected", e);
        }
    }


    @Override
    public boolean delete( @NotNull RoomDTO entity ) throws RepoException {
        String name = entity.getName();
        String sql = "DELETE FROM `room` WHERE name = ?";
        try ( PreparedStatement stm = Database.getConnection().prepareStatement(sql) ) {
            stm.setString(1, name);
            stm.execute(sql);
            return true;
        } catch ( SQLException | DatabaseConnectionException e ) {
            throw new RepoException("Deleting room failed, no rows affected", e);
        }
    }
}
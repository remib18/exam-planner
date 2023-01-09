package fr.univtours.examplanner.repositories;


import fr.univtours.examplanner.entities.dtos.RoomDTO;
import fr.univtours.examplanner.entities.dtos.SlotDTO;
import fr.univtours.examplanner.exceptions.DatabaseConnectionException;
import fr.univtours.examplanner.exceptions.MappingException;
import fr.univtours.examplanner.exceptions.RepoException;
import fr.univtours.examplanner.mappers.SlotMapper;
import fr.univtours.examplanner.utils.Database;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class SlotRepo implements BaseRepo<SlotDTO, String> {

    /**
     * Récupère tous les créneaux parmi une salle
     *
     * @param room une salle
     * @return la liste des créneaux correspondants
     */
    public @NotNull List< SlotDTO > getAllFromRoom( @NotNull RoomDTO room ) throws RepoException {
        try {
            String sql = "SELECT * FROM _slottoroom WHERE room = " + room.getName();
            ResultSet res = Database.getConnection().createStatement().executeQuery(sql);
            return ( new SlotMapper() ).entityToDTO(res);
        } catch ( SQLException | DatabaseConnectionException | MappingException e ) {
            throw new RepoException("Getting slots failed, no rows affected.", e);
        }
    }

    @Override
    public @NotNull SlotDTO save( @NotNull SlotDTO entity ) throws RepoException {
        boolean hasId = !Objects.isNull(entity.getId());
        String id = hasId ? entity.getId() : Database.getNewUUID();
        String sql;
        if ( hasId ) {
            sql = "INSERT INTO slot (id, day, hour, duration) VALUES (" + id + ", " + "?, ?," + " ?, ?)";
        } else {
            sql = "UPDATE slot SET day = ?, hour = ?, duration = ? WHERE id = " + id;
        }
        try ( PreparedStatement pstmt = Database.getConnection().prepareStatement(sql) ) {


            pstmt.setString(1, entity.getStart().toString());
            pstmt.setString(1, SlotDTO.getCalendarDate(entity.getStart()));
            pstmt.setFloat(2, SlotDTO.getHourMins(entity.getStart()));
            pstmt.setFloat(3, entity.getDuration());

            int rows = pstmt.executeUpdate();
            if ( 0 == rows ) {
                throw new RepoException("Creating slot failed, no rows affected", null);
            }
            entity.setId(entity.getId());
            return entity;
        } catch ( DatabaseConnectionException | SQLException e ) {
            throw new RepoException("Fail to save", e);
        }
    }

    @Override
    public @NotNull List< SlotDTO > getAll() throws RepoException {
        return getAllFrom(null, null);
    }

    private @NotNull List< SlotDTO > getAllFrom( @Nullable String key, @Nullable String value ) throws RepoException {
        String sql = "SELECT * FROM slot";
        boolean withOptions = !Objects.isNull(key) && !Objects.isNull(value);
        if ( withOptions ) {
            sql += " WHERE " + key + " = ?";
        }
        try ( PreparedStatement stm = Database.getConnection().prepareStatement(sql) ) {
            if ( withOptions ) {
                stm.setString(1, value);
            }
            return ( new SlotMapper() ).entityToDTO(stm.executeQuery());
        } catch ( SQLException | DatabaseConnectionException | MappingException e ) {
            throw new RepoException("Getting slots failed, no rows affected.", e);
        }
    }

    @Override
    public @NotNull SlotDTO getById( @NotNull String id ) throws RepoException {
        List< SlotDTO > res = getAllFrom("id", id);
        if ( res.isEmpty() ) {
            return null;
        }
        return res.get(0);
    }


    public @NotNull List< SlotDTO > getFromStart( @NotNull Calendar start )
    throws RepoException, DatabaseConnectionException, SQLException, MappingException {
        String jour = SlotDTO.getCalendarDate(start);
        String hour = String.valueOf(SlotDTO.getHourMins(start));
        String sql = "SELECT * FROM _slottoroom WHERE date = " + jour + " AND heure = " + hour;
        ResultSet res = Database.getConnection().createStatement().executeQuery(sql);
        return ( new SlotMapper() ).entityToDTO(res);
    }

    public @NotNull SlotDTO getByDuration( @NotNull float duration ) throws RepoException {
        List< SlotDTO > res = getAllFrom("duration", String.valueOf(duration));
        if ( res.isEmpty() ) {
            return null;
        }
        return res.get(0);
    }

    @Override
    public boolean delete( @NotNull SlotDTO entity ) throws RepoException {
        String sql = "DELETE FROM slot WHERE id = ?";
        try ( PreparedStatement stm = Database.getConnection().prepareStatement(sql) ) {
            stm.setString(1, entity.getId());
            int rows = stm.executeUpdate();
            return 0 != rows;
        } catch ( SQLException | DatabaseConnectionException e ) {
            throw new RepoException("Deleting user failed, no rows affected.", e);
        }
    }


}
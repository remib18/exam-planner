package fr.univtours.examplanner.repositories;

import fr.univtours.examplanner.entities.dtos.ExamDTO;
import fr.univtours.examplanner.entities.dtos.GroupDTO;
import fr.univtours.examplanner.entities.dtos.ManagerDTO;
import fr.univtours.examplanner.enums.ExamType;
import fr.univtours.examplanner.exceptions.DatabaseConnectionException;
import fr.univtours.examplanner.exceptions.MappingException;
import fr.univtours.examplanner.exceptions.RepoException;
import fr.univtours.examplanner.mappers.ExamMapper;
import fr.univtours.examplanner.utils.Database;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.*;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ExamRepo implements BaseRepo< ExamDTO, String> {

    private @NotNull List< ExamDTO > getAllFrom( @Nullable String key, @Nullable String value ) throws RepoException {
        String sql = "SELECT * FROM exam";
        boolean withOptions = !Objects.isNull(key) && !Objects.isNull(value);
        if ( withOptions ) {
            sql += " WHERE " + key + " = ?";
        }
        try ( PreparedStatement stm = Database.getConnection().prepareStatement(sql) ) {
            if ( withOptions ) {
                stm.setString(1, value);
            }
            return ExamMapper.EntityToTDO(stm.executeQuery());
        } catch ( SQLException | DatabaseConnectionException | MappingException e ) {
            throw new RepoException("Getting users failed, no rows affected.", e);
        }
    }

    @Override
    public void save( @NotNull ExamDTO entity) throws RepoException {
        Boolean hasId = !Objects.isNull(entity.getId());
        String id = hasId ? entity.getId() : Database.getNewUUID();
        String sql;
        if ( hasId ){
            sql = "INSERT INTO Exam (id, name, duration, type, subject) VALUES (" + id + ", ?, ?, ?, ?)";
        } else {
            sql = "UPDATE Exam SET name = ?, duration = ?, type = ?, subject = ? WHERE id = " + id;
        }
        try ( PreparedStatement pstmt = Database.getConnection().prepareStatement(sql)){
            pstmt.setString(1, entity.getName());
            pstmt.setFloat(2, entity.getDuration());
            pstmt.setString(3, entity.getType().getTypeString());
            pstmt.setString(4, entity.getSubject());
        } catch ( DatabaseConnectionException | SQLException e) {
            throw new RepoException("Fail", e);
        }
        throw new UnsupportedOperationException();
    }

    /**
     * Récupère tous les examens enfants
     *
     * @param exam un examen
     * @return la liste des examens correspondants
     */
    public @NotNull List< ExamDTO > getAllFromExam( @NotNull ExamDTO exam) throws RepoException {
        try {
            String sql = "SELECT child FROM _examtoexam WHERE parent = " + exam.getId();
            ResultSet previousExamsIds = Database.getConnection().createStatement().executeQuery(sql);
            return ExamMapper.EntityToTDO(previousExamsIds);
        } catch ( SQLException | DatabaseConnectionException | MappingException e ) {
            throw new RepoException("Fail", e);
        }
    }

    public @NotNull List< ExamDTO > getAllFromGroup( @NotNull GroupDTO group) throws RepoException {
        try {
            String sql = "SELECT exam FROM _examtogroup WHERE group = " + group.getId();
            ResultSet examsIds = Database.getConnection().createStatement().executeQuery(sql);
            return ExamMapper.EntityToTDO(examsIds);
        } catch ( SQLException | DatabaseConnectionException | MappingException e ) {
            throw new RepoException("Fail", e);
        }
    }

    public @NotNull List< ExamDTO > getAllFromManager( @NotNull ManagerDTO manager) throws RepoException {
        try {
            String sql = "SELECT exam FROM _examtomanager WHERE manager = " + manager.getId();
            ResultSet examsIds = Database.getConnection().createStatement().executeQuery(sql);
            return ExamMapper.EntityToTDO(examsIds);
        } catch ( SQLException | DatabaseConnectionException | MappingException e ) {
            throw new RepoException("Fail", e);
        }
    }

    @Override
    public @NotNull List< ExamDTO > getAll() throws RepoException {
        return getAllFrom(null, null);
    }

    @Override
    public @NotNull ExamDTO getById( @NotNull String id) throws RepoException {
       List< ExamDTO > res = getAllFrom("id", id);
       if ( res.isEmpty() ) {
           return null;
       }
       return res.get(0);
   }

    public @NotNull ExamDTO getByName( @NotNull String name ) throws RepoException {
        List< ExamDTO > res = getAllFrom("name", name);
        if ( res.isEmpty() ) {
            return null;
        }
        return res.get(0);
    }

    public @NotNull ExamDTO getByDuration( @NotNull float duration ) throws RepoException {
        List< ExamDTO > res = getAllFrom("duration", String.valueOf(duration));
        if ( res.isEmpty() ) {
            return null;
        }
        return res.get(0);
    }

    public @NotNull List< ExamDTO > getAllForType( ExamType type ) throws RepoException {
        return getAll().stream().filter(user -> user.getType().equals(type)).toList();
    }

    @Override
    public boolean delete(@NotNull ExamDTO entity) throws RepoException {
        try {
            Connection conn = Database.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("DELETE * FROM Exam WHERE name = " + entity.getId());
            return true;
        } catch ( DatabaseConnectionException | SQLException e ) {
            throw new RepoException("Fail", e);
        }
    }
}
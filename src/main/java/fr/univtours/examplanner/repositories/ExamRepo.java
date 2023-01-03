package fr.univtours.examplanner.repositories;

import fr.univtours.examplanner.entities.dtos.ExamDTO;
import fr.univtours.examplanner.exceptions.DatabaseConnectionException;
import fr.univtours.examplanner.exceptions.RepoException;
import fr.univtours.examplanner.mappers.ExamMapper;
import fr.univtours.examplanner.utils.Database;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExamRepo implements BaseRepo< ExamDTO, String> {
    //TODO @gab : remplacer RuntimeException par repoException (apres merge remi)

    /**
     * Récupère tous les examens
     *
     * @param exam un examen
     * @return la liste des examens correspondants
     */
    public @NotNull List< ExamDTO > getAllFromExam( @NotNull ExamDTO exam) throws RepoException {
        try {
            Connection conn = Database.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM _examtoexam WHERE parent = " + exam.getId());
            return ExamMapper.EntityToTDO(rs);
        } catch ( DatabaseConnectionException | SQLException e ) {
            throw new RepoException("Fail", e);
        }
    }

    @Override
    public @NotNull ExamDTO save( @NotNull ExamDTO entity) throws RepoException {
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

    @Override
    public @NotNull List< ExamDTO > getAll() throws RepoException {
        // TODO implement here
        try {
            Connection conn = Database.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM Department");
            return ExamMapper.EntityToTDO(rs);
        } catch ( DatabaseConnectionException | SQLException e ) {
            throw new RepoException("Fail", e);
        }
    }

   @Override
    public @NotNull ExamDTO getById( @NotNull String id) {
       try {
           Connection conn = Database.getConnection();
           Statement stm = conn.createStatement();
           ResultSet rs = stm.executeQuery("SELECT * FROM exam WHERE id = " + id);
           return ExamMapper.EntityToTDO(rs).get(0);
       } catch ( DatabaseConnectionException | SQLException e ) {
           throw new RuntimeException("Fail", e);
       }
   }

    @Override
    public boolean delete(@NotNull ExamDTO entity) {
        try {
            Connection conn = Database.getConnection();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("DELETE * FROM Exam WHERE name = " + entity.getId());
            return true;
        } catch ( DatabaseConnectionException | SQLException e ) {
            throw new RuntimeException("Fail", e);
        }
    }
}
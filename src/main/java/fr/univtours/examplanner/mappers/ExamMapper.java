package fr.univtours.examplanner.mappers;

import fr.univtours.examplanner.entities.dtos.ExamDTO;
import fr.univtours.examplanner.enums.ExamType;
import fr.univtours.examplanner.exceptions.DatabaseConnectionException;
import fr.univtours.examplanner.exceptions.MappingException;
import fr.univtours.examplanner.utils.Database;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExamMapper implements BaseMapper {
    /**
     * Créée une nouvelle classe FinalExamDTO(id, name, groups, managers, subject, duration, previousExams) grâce aux
     * éléments de la requête SQL (entities)
     *
     * @param entities = résultats de la requête SQL
     * @return = classe {@link ExamDTO}
     */
    public static @NotNull List< ExamDTO> EntityToTDO( ResultSet entities ) throws MappingException {
        List <ExamDTO> exams = new ArrayList<>();
        try {
            while ( entities.next() ) {
                String id = entities.getString("id");
                String name = entities.getString("name");
                float duration = entities.getFloat("duration");
                ExamType type = ExamType.setTypeByString(entities.getString("type"));
                String subject = entities.getString("subject");

                Connection conn = Database.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rsEtE = stmt.executeQuery("SELECT * FROM _ExamToExam WHERE parent = " + id);
                List< String > previousExamsIds = new ArrayList<>();
                while ( rsEtE.next() ) {
                    previousExamsIds.add(rsEtE.getString("child"));
                }
                ResultSet rsEtG = stmt.executeQuery("SELECT * FROM _ExamToGroup WHERE exam = " + id);
                List< String > groupsIds = new ArrayList<>();
                while ( rsEtE.next() ) {
                    previousExamsIds.add(rsEtE.getString("group"));
                }
                ResultSet rsEtM = stmt.executeQuery("SELECT * FROM _ExamToManager WHERE exam = " + id);
                List< String > managersIds = new ArrayList<>();
                while ( rsEtE.next() ) {
                    previousExamsIds.add(rsEtE.getString("manager"));
                }
                ExamDTO exam = new ExamDTO(id, name, type, groupsIds, managersIds, subject, duration, previousExamsIds);
                exams.add(exam);
            }
            return exams;
        } catch ( SQLException | DatabaseConnectionException e ) {
            throw new MappingException("Fail", e);
        }
    }
    }
package fr.univtours.examplanner.mappers;

import fr.univtours.examplanner.entities.dtos.ExamDTO;
import fr.univtours.examplanner.enums.ExamType;
import fr.univtours.examplanner.exceptions.DatabaseConnectionException;
import fr.univtours.examplanner.exceptions.MappingException;
import fr.univtours.examplanner.utils.Database;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    @Override
    public @NotNull List< ExamDTO> entityToDTO( ResultSet entities ) throws MappingException {

        List <ExamDTO> exams = new ArrayList<>();
        try {
            if ( entities.next() ) {
                while ( entities.next() ) {
                    String id = entities.getString("id");
                    String name = entities.getString("name");
                    float duration = entities.getFloat("duration");
                    ExamType type = ExamType.parse(entities.getString("type"));
                    String subject = entities.getString("subject");

                    Connection conn = Database.getConnection();
                    Statement stmt = conn.createStatement();
                    ResultSet rsEtE = stmt.executeQuery("SELECT * FROM _ExamToExam WHERE parent = '" + id +"'");
                    List< String > previousExamsIds = new ArrayList<>();
                    while ( rsEtE.next() ) {
                        previousExamsIds.add(rsEtE.getString("child"));
                    }
                    ResultSet rsEtG = stmt.executeQuery("SELECT * FROM _ExamToGroup WHERE exam = '" + id +"'");
                    List< String > groupsIds = new ArrayList<>();
                    while ( rsEtG.next() ) {
                        groupsIds.add(rsEtE.getString("group"));
                    }
                    ResultSet rsEtM = stmt.executeQuery("SELECT * FROM _ExamToManager WHERE exam = '" + id +"'");
                    List< String > managersIds = new ArrayList<>();
                    while ( rsEtM.next() ) {
                        managersIds.add(rsEtE.getString("manager"));
                    }
                    ExamDTO exam = new ExamDTO(id,
                            name,
                            type,
                            groupsIds,
                            managersIds,
                            subject,
                            duration,
                            previousExamsIds
                    );
                    exams.add(exam);
                }
                return exams;
            }
            throw new MappingException("No result to map", null);
        } catch ( SQLException | DatabaseConnectionException e ) {
            throw new MappingException("Fail", e);
        }
    }
    }
package fr.univtours.examplanner.mappers;

import fr.univtours.examplanner.entities.dtos.FinalExamDTO;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;

public class ExamMapper implements BaseMapper{

    /**
     * Créée une nouvelle classe FinalExamDTO(id, name, groups, managers, subject, duration, previousExams)
     * grâce aux éléments de la requête SQL (entities)
     *
     * @param entities = résultats de la requête SQL
     * @return = classe {@link FinalExamDTO}
     */
    public @NotNull FinalExamDTO EntityToDTO(@NotNull ResultSet entities) {
        // TODO implement here
        throw new UnsupportedOperationException();
    }

    /**
     * Permet l'utilisation des attributs de la classe FinalExamDTO(id, name, groups, managers, subject, duration, previousExams)
     * dans une requête SQL
     *
     * @param dto = attributs de la classe {@link FinalExamDTO}
     * @return = éléments nécessaires à une requête SQL
     */
    public  @NotNull String[] DTOToEntity( @NotNull FinalExamDTO dto) {
        // TODO implement here
        throw new UnsupportedOperationException() ;
    }

}
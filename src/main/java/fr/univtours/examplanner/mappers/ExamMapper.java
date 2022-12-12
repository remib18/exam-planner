package fr.univtours.examplanner.mappers;

import fr.univtours.examplanner.entities.dtos.FinalExamDTO;
import org.jetbrains.annotations.NotNull;

public class ExamMapper implements BaseMapper{

    // Todo(@gab): Remplacer Object avec retour de Benoit (repo)

    /**
     * Créée une nouvelle classe FinalExamDTO(id, name, groups, managers, subject, duration, previousExams)
     * grâce aux éléments de la requête SQL (entity)
     *
     * @param entity = résultats de la requête SQL
     * @return = classe {@link FinalExamDTO}
     */
    public @NotNull FinalExamDTO EntityToDTO(@NotNull Object entity) {
        // TODO implement here
        throw new UnsupportedOperationException();
    }

    // Todo(@gab): same
    /**
     * Permet l'utilisation des attributs de la classe FinalExamDTO(id, name, groups, managers, subject, duration, previousExams)
     * dans une requête SQL
     *
     * @param dto = attributs de la classe {@link FinalExamDTO}
     * @return = éléments nécessaires à une requête SQL
     */
    public  @NotNull Object DTOToEntity(@NotNull FinalExamDTO dto) {
        // TODO implement here
        throw new UnsupportedOperationException() ;
    }

}
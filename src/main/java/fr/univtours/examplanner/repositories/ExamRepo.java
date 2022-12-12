package fr.univtours.examplanner.repositories;

import fr.univtours.examplanner.entities.Exam;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ExamRepo implements BaseRepo<Exam, String> {

    /**
     * Récupère tous les examens
     *
     * @param e un examen
     * @return la liste des examens correspondants
     */
    public @NotNull List<Exam> getAllFromExam(@NotNull Exam e) {
        // TODO implement here
        return new ArrayList<>();
    }

    @Override
    public @NotNull Exam save(@NotNull Exam entity) {
        // TODO implement here
        throw new UnsupportedOperationException();
    }

    @Override
    public @NotNull List<Exam> getAll() {
        // TODO implement here
        return new ArrayList<>();
    }

   @Override
    public @NotNull Exam getById(@NotNull String id) {
        // TODO implement here
       throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(@NotNull Exam entity) {
        // TODO implement here
        return false;
    }

}
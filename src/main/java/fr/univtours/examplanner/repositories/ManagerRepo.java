package fr.univtours.examplanner.repositories;

import fr.univtours.examplanner.entities.Exam;
import fr.univtours.examplanner.entities.dtos.ManagerDTO;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ManagerRepo implements BaseRepo<ManagerDTO, String> {

    /**
     * Récupère tous les examens
     *
     * @param e un examen
     * @return la liste des managers correspondants à l'examen
     */
    public @NotNull List<ManagerDTO> getAllFromExam(@NotNull Exam e) {
        // TODO implement here
        return new ArrayList<>();
    }
    @Override
    public @NotNull ManagerDTO save(@NotNull ManagerDTO entity) {
        // TODO implement here
        throw new UnsupportedOperationException();
    }

    @Override
    public @NotNull List<ManagerDTO> getAll() {
        // TODO implement here
        return new ArrayList<>();
    }

   @Override
    public @NotNull ManagerDTO getById(@NotNull String id) {
        // TODO implement here
       throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(@NotNull ManagerDTO entity) {
        // TODO implement here
        return false;
    }

}
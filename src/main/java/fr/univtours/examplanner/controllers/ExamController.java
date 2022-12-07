package fr.univtours.examplanner.controllers;

import fr.univtours.examplanner.entities.Exam;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ExamController implements BaseController<Exam> {

    // todo(@benoît): Repo
    /**
     * Interface avec la base de données permettant d'effectuer des opérations standards sur les examens
     */
    @NotNull
    private final Object repo;

    public ExamController() {
        repo = new Object();
    }

    /**
     * Liste les examens
     *
     * @return la liste des examens
     */
    public @NotNull List<Exam> getAll() {
        // TODO implement here
        return new ArrayList<>();
    }

    /**
     * Crée un examen
     *
     * @param name            le nom du département
     * @param roomName        le nom de la salle
     * @param departmentName  le nom du département
     * @param duration        le temps de l'examen
     * @param groupIds        la liste des identifiants des groupes
     * @param managerIds      la liste des identifiants des surveillants
     * @param previousExamIds la liste des identifiants des examens devant se dérouler plus tôt
     * @return l'examen créé
     */
    public @NotNull Exam create(
            @NotNull String name,
            @NotNull String roomName,
            @NotNull String departmentName,
            float duration,
            @NotNull List<Integer> groupIds,
            @NotNull List<Integer> managerIds,
            @NotNull List<Integer> previousExamIds
    ) {
        // TODO implement here
        throw new UnsupportedOperationException();
    }

    /**
     * Modifie un examen
     *
     * @param entity l'examen à modifier
     */
    public void save(@NotNull Exam entity) throws Exception {
        // TODO implement here
        throw new Exception();
    }

    /**
     * Supprime un examen
     *
     * @param entity l'examen à supprimer
     */
    public void delete(@NotNull Exam entity) throws Exception {
        // TODO implement here
        throw new Exception();
    }


}
package fr.univtours.examplanner.controllers;

import fr.univtours.examplanner.entities.dtos.DepartmentDTO;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DepartmentController implements BaseController<DepartmentDTO> {

    // todo(@benoît): Repo
    /**
     * Interface avec la base de données permettant d'effectuer des opérations standards sur les départements
     */
    @NotNull
    private final DepartmentRepo repo;

    public DepartmentController() {
        repo = new Object();
    }

    /**
     * Liste les départements
     *
     * @return la liste des départements
     */
    public @NotNull List<DepartmentDTO> getAll() {
        // TODO implement here
        return new ArrayList<>();
    }

    /**
     * Crée un département
     *
     * @param name le nom du département
     * @return le département créé
     */
    public @NotNull DepartmentDTO create(@NotNull String name) {
        // TODO implement here
        throw new UnsupportedOperationException();
    }

    /**
     * Modifie un département
     *
     * @param entity le département à modifier
     */
    public void save(@NotNull DepartmentDTO entity) throws Exception {
        // TODO implement here
        throw new Exception();
    }

    /**
     * Supprime un département
     *
     * @param entity le département à supprimer
     */
    public void delete(@NotNull DepartmentDTO entity) throws Exception {
        // TODO implement here
        throw new Exception();
    }

}
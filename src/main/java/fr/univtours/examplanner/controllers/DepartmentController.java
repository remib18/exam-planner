package fr.univtours.examplanner.controllers;

import fr.univtours.examplanner.entities.dtos.DepartmentDTO;
import fr.univtours.examplanner.exceptions.ControllerException;
import fr.univtours.examplanner.repositories.DepartmentRepo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DepartmentController implements BaseController<DepartmentDTO> {

    /**
     * Interface avec la base de données permettant d'effectuer des opérations standards sur les départements
     */
    @NotNull
    private final DepartmentRepo repo;

    public DepartmentController() {
        repo = new DepartmentRepo();
    }

    /**
     * Liste les départements
     *
     * @return la liste des départements
     */
    public @NotNull List< DepartmentDTO > getAll() {
        // TODO implement here
        return new ArrayList<>();
    }

    /**
     * Obtient un département
     *
     * @param id l'identifiant du département
     * @return le département correspondant à l'identifiant
     */
    public @Nullable DepartmentDTO getByID( String id ) {
        // TODO implement here
        throw new UnsupportedOperationException();
    }

    /**
     * Crée un département
     *
     * @param name le nom du département
     * @return le département créé
     */
    public @NotNull DepartmentDTO create( @NotNull String name ) {
        // TODO implement here
        throw new UnsupportedOperationException();
    }

    /**
     * Modifie un département
     *
     * @param entity le département à modifier
     */
    public void save( @NotNull DepartmentDTO entity ) throws ControllerException {
        // TODO implement here
        throw new UnsupportedOperationException();
    }

    /**
     * Supprime un département
     *
     * @param entity le département à supprimer
     */
    public void delete( @NotNull DepartmentDTO entity ) throws ControllerException {
        // TODO implement here
        throw new UnsupportedOperationException();
    }

}
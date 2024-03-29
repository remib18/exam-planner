package fr.univtours.examplanner.controllers;

import fr.univtours.examplanner.entities.dtos.DepartmentDTO;
import fr.univtours.examplanner.exceptions.ControllerException;
import fr.univtours.examplanner.exceptions.RepoException;
import fr.univtours.examplanner.repositories.DepartmentRepo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DepartmentController {

    /**
     * Interface avec la base de données permettant d'effectuer des opérations standards sur les départements
     */
    @NotNull
    private final DepartmentRepo repo;

    private static DepartmentController instance;

    public DepartmentController() {
        super();
        repo = new DepartmentRepo();
    }

    private static DepartmentController getInstance() {
        if ( Objects.isNull(instance) ) {
            instance = new DepartmentController();
        }
        return instance;
    }

    /**
     * Liste les départements
     *
     * @return la liste des départements
     */
    public static @NotNull List< DepartmentDTO > getAll() {
        try {
            return getInstance().repo.getAll();
        } catch ( RepoException e ) {
            return new ArrayList<>();
        }
    }

    public static @NotNull DepartmentDTO getByName( @NotNull String name ) throws ControllerException {
        try {
            return getInstance().repo.getById(name);
        } catch ( RepoException e ) {
            throw new ControllerException("Fail to fetch", e);
        }
    }

    /**
     * Obtient un département
     *
     * @param id l'identifiant du département
     * @return le département correspondant à l'identifiant
     */
    public static @Nullable DepartmentDTO getByID( String id ) {
        // TODO implement here
        throw new UnsupportedOperationException();
    }

    /**
     * Crée un département
     *
     * @param name le nom du département
     * @return le département créé
     */
    public static @NotNull DepartmentDTO create( @NotNull String name ) throws ControllerException {
        try {
            return getInstance().repo.save(new DepartmentDTO(name));
        } catch ( RepoException e ) {
            throw new ControllerException("Fail to fetch", e);
        }
    }

    /**
     * Modifie un département
     *
     * @param entity le département à modifier
     */
    public static void save( @NotNull DepartmentDTO entity ) throws ControllerException {
        try {
            getInstance().repo.save(entity);
        } catch ( RepoException e ) {
            throw new ControllerException("Fail to save", e);
        }
    }

    /**
     * Supprime un département
     *
     * @param entity le département à supprimer
     */
    public static void delete( @NotNull DepartmentDTO entity ) throws ControllerException {
        try {
            getInstance().repo.delete(entity);
        } catch ( RepoException e ) {
            throw new ControllerException("Fail to delete", e);
        }
    }

}
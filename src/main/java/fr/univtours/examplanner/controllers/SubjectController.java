package fr.univtours.examplanner.controllers;

import fr.univtours.examplanner.entities.dtos.SubjectDTO;
import fr.univtours.examplanner.exceptions.ControllerException;
import fr.univtours.examplanner.exceptions.RepoException;
import fr.univtours.examplanner.repositories.SubjectRepo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class SubjectController {

    private static SubjectController instance;

    /**
     * Interface avec la base de données permettant d'effectuer des opérations standards sur les matières
     */

    private final SubjectRepo repo;

    public SubjectController() {
        repo = new SubjectRepo();
    }

    /**
     * liste les matières
     *
     * @return La liste des matières
     *
     * @throws ControllerException Si une erreur survient
     */

    public static @NotNull List< SubjectDTO > getAll() throws ControllerException {
        try {
            return getInstance().repo.getAll();
        } catch ( RepoException e ) {
            throw new ControllerException("An error occurred during the data fetching", e);
        }
    }

    private static SubjectController getInstance() {
        if ( Objects.isNull(instance) ) {
            instance = new SubjectController();
        }
        return instance;
    }

    public static @Nullable SubjectDTO getById( @NotNull String id ) throws ControllerException {
        try {
            return getInstance().repo.getById(id);
        } catch ( RepoException e ) {
            throw new ControllerException("An error occurred during the data fetching", e);
        }
    }

    /**
     * Permet de créer une matière
     *
     * @param name Le nom de la matière
     * @return La matière créée
     *
     * @throws ControllerException Si une erreur survient
     */

    public static @NotNull SubjectDTO create( @NotNull String name ) throws ControllerException {
        try {
            return getInstance().repo.save(new SubjectDTO(null, name));
        } catch ( RepoException e ) {
            throw new ControllerException("An error occurred during the data saving", e);
        }
    }

    /**
     * Permet de modifier ou créer une matière
     *
     * @param entity La matière à modifier
     * @throws ControllerException Si une erreur survient
     */

    public static void save( @NotNull SubjectDTO entity ) throws ControllerException {
        try {
            getInstance().repo.save(entity);
        } catch ( RepoException e ) {
            throw new ControllerException("An error occurred during the data saving", e);
        }
    }

    /**
     * Permet de supprimer une matière
     *
     * @param entity La matière à supprimer
     * @throws ControllerException SI une erreur survient
     */

    public static void delete( @NotNull SubjectDTO entity ) throws ControllerException {
        try {
            getInstance().repo.delete(entity);
        } catch ( RepoException e ) {
            throw new ControllerException("An error occurred during the data deletion.", e);
        }
    }

}

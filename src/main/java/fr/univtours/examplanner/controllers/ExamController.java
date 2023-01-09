package fr.univtours.examplanner.controllers;

import fr.univtours.examplanner.entities.dtos.ExamDTO;
import fr.univtours.examplanner.entities.dtos.GroupDTO;
import fr.univtours.examplanner.entities.dtos.ManagerDTO;
import fr.univtours.examplanner.enums.ExamType;
import fr.univtours.examplanner.exceptions.ControllerException;
import fr.univtours.examplanner.exceptions.RepoException;
import fr.univtours.examplanner.repositories.ExamRepo;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class ExamController {

    /**
     * Interface avec la base de données permettant d'effectuer des opérations standards sur les examens
     */
    @NotNull
    private final ExamRepo repo;

    private static ExamController instance;

    private ExamController() {
        super();
        repo = new ExamRepo();
    }

    private static ExamController getInstance() {
        if ( Objects.isNull(instance) ) {
            instance = new ExamController();
        }
        return instance;
    }

    /**
     * Liste les examens
     *
     * @return la liste des examens
     */
    public static @NotNull List< ExamDTO > getAll() throws ControllerException {
        try {
            return getInstance().repo.getAll();
        } catch ( RepoException e ) {
            throw new ControllerException("Fail to fetch", e);
        }
    }

    public static @NotNull List< ExamDTO > getAllFromExam( @NotNull ExamDTO exam ) throws ControllerException {
        try {
            return getInstance().repo.getAllFromExam(exam);
        } catch ( RepoException e ) {
            throw new ControllerException("Fail to fetch", e);
        }
    }

    public static @NotNull List< ExamDTO > getAllFromGroup( @NotNull GroupDTO group ) throws ControllerException {
        try {
            return getInstance().repo.getAllFromGroup(group);
        } catch ( RepoException e ) {
            throw new ControllerException("Fail to fetch", e);
        }
    }

    public static @NotNull List< ExamDTO > getAllFromManager( @NotNull ManagerDTO manager ) throws ControllerException {
        try {
            return getInstance().repo.getAllFromManager(manager);
        } catch ( RepoException e ) {
            throw new ControllerException("Fail to fetch", e);
        }
    }

    public static @NotNull ExamDTO getById( @NotNull String id ) throws ControllerException {
        try {
            return getInstance().repo.getById(id);
        } catch ( RepoException e ) {
            throw new ControllerException("Fail to fetch", e);
        }
    }

    public static @NotNull ExamDTO getByName( @NotNull String name ) throws ControllerException {
        try {
            return getInstance().repo.getByName(name);
        } catch ( RepoException e ) {
            throw new ControllerException("Fail to fetch", e);
        }
    }

    public static @NotNull ExamDTO getByDuration( @NotNull float duration ) throws ControllerException {
        try {
            return getInstance().repo.getByDuration(duration);
        } catch ( RepoException e ) {
            throw new ControllerException("Fail to fetch", e);
        }
    }

    public static @NotNull List< ExamDTO > getAllForType( @NotNull ExamType type ) throws ControllerException {
        try {
            return getInstance().repo.getAllForType(type);
        } catch ( RepoException e ) {
            throw new ControllerException("Fail to fetch", e);
        }
    }

    /**
     * Modifie un examen
     *
     * @param entity l'examen à modifier
     */
    public static void save( @NotNull ExamDTO entity ) throws ControllerException {
        try {
            getInstance().repo.save(entity);
        } catch ( RepoException e ) {
            throw new ControllerException("Fail to save", e);
        }
    }

    /**
     * Supprime un examen
     *
     * @param entity l'examen à supprimer
     */
    public static void delete( @NotNull ExamDTO entity ) throws ControllerException {
        try {
            getInstance().repo.delete(entity);
        } catch ( RepoException e ) {
            throw new ControllerException("Fail to delete", e);
        }
    }


}
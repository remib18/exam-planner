package fr.univtours.examplanner.controllers;

import fr.univtours.examplanner.entities.dtos.SlotDTO;
import fr.univtours.examplanner.exceptions.ControllerException;
import fr.univtours.examplanner.exceptions.RepoException;
import fr.univtours.examplanner.repositories.SlotRepo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class SlotController {

    /**
     * Interface avec la base de données permettant d'effectuer des opérations standards sur les horaires
     */
    @NotNull
    private final SlotRepo repo;

    private SlotController() {
        repo = new SlotRepo();
    }

    private static SlotController instance;

    public static @Nullable SlotDTO getByID( @NotNull String id ) throws ControllerException {
        try {
            return getInstance().repo.getById(id);
        } catch ( RepoException e ) {
            throw new ControllerException("An error occurred during the data fetching.", e);
        }

    }

    /**
     * Liste les horaires
     *
     * @return la liste des horaires
     */
    public static @NotNull List< SlotDTO > getAll() throws ControllerException {
        try {
            return getInstance().repo.getAll();
        } catch ( RepoException e ) {
            throw new ControllerException("An error occurred during the data fetching.", e);
        }
    }

    private static SlotController getInstance() {
        if ( Objects.isNull(instance) ) {
            instance = new SlotController();
        }
        return instance;
    }

    /**
     * Crée un horaire
     *
     * @param start         moment du debut du créneau
     * @param durationFloat la durée du créneau
     * @return l'horaire créé
     */
    public static SlotDTO create(
            @NotNull Date start, float hour, float durationFloat
    ) throws ControllerException {
        try {
            return getInstance().repo.save(new SlotDTO(null, start, hour, durationFloat));
        } catch ( RepoException e ) {
            throw new ControllerException("An error occurred during the data saving.", e);
        }
    }

    /**
     * Modifie l'horaire
     *
     * @param entity l'horaire à modifier
     */
    public static void save( @NotNull SlotDTO entity ) throws ControllerException {
        try {
            getInstance().repo.save(entity);
        } catch ( RepoException e ) {
            throw new ControllerException("An error occurred during the data saving.", e);
        }
    }

    public static SlotDTO getByDuration( @NotNull Float duration ) throws ControllerException {
        try {
            return getInstance().repo.getByDuration(duration);
        } catch ( RepoException e ) {
            throw new ControllerException("An error occurred during the data saving.", e);
        }
    }

    /**
     * Supprime un horaire
     *
     * @param entity l'horaire à modifier
     */
    public static void delete( @NotNull SlotDTO entity ) throws ControllerException {
        try {
            getInstance().repo.delete(entity);
        } catch ( RepoException e ) {
            throw new ControllerException("An error occurred during the data deletion.", e);
        }
    }
}
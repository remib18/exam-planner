package fr.univtours.examplanner.controllers;

import fr.univtours.examplanner.entities.dtos.SlotDTO;
import fr.univtours.examplanner.exceptions.ControllerException;
import fr.univtours.examplanner.exceptions.DatabaseConnectionException;
import fr.univtours.examplanner.exceptions.MappingException;
import fr.univtours.examplanner.exceptions.RepoException;
import fr.univtours.examplanner.repositories.SlotRepo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class SlotController implements BaseController<SlotDTO> {

    /**
     * Interface avec la base de données permettant d'effectuer des opérations standards sur les horaires
     */
    @NotNull
    private final SlotRepo repo;

    public SlotController() {
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

    public static @Nullable List< SlotDTO > getFromStart( @NotNull Calendar start ) throws ControllerException {
        try {
            return getInstance().repo.getFromStart(start);
        } catch ( RepoException | DatabaseConnectionException | MappingException | SQLException e ) {
            throw new ControllerException("An error occurred during the data fetching.", e);
        }
    }

    /**
     * Liste les horaires
     *
     * @return la liste des horaires
     */
    public @NotNull List< SlotDTO > getAll() throws ControllerException {
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
    public SlotDTO create(
            @NotNull Calendar start, @NotNull Float durationFloat
    ) throws ControllerException {
        try {

            return getInstance().repo.save(new SlotDTO(null, start, durationFloat));
        } catch ( RepoException | DatabaseConnectionException | SQLException e ) {
            throw new ControllerException("An error occurred during the data saving.", e);
        }
    }

    /**
     * Modifie l'horaire
     *
     * @param entity l'horaire à modifier
     */
    public void save( @NotNull SlotDTO entity ) throws ControllerException {
        try {
            getInstance().repo.save(entity);
        } catch ( RepoException | SQLException | DatabaseConnectionException e ) {
            throw new ControllerException("An error occurred during the data saving.", e);
        }
    }

    public SlotDTO getByDuration( @NotNull Float duration ) throws ControllerException {
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
    public void delete( @NotNull SlotDTO entity ) throws ControllerException {
        try {
            getInstance().repo.delete(entity);
        } catch ( RepoException e ) {
            throw new ControllerException("An error occurred during the data deletion.", e);
        }
    }
}
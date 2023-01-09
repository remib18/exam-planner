package fr.univtours.examplanner.controllers;

import fr.univtours.examplanner.entities.dtos.RoomDTO;
import fr.univtours.examplanner.entities.dtos.SlotDTO;
import fr.univtours.examplanner.exceptions.ControllerException;
import fr.univtours.examplanner.exceptions.RepoException;
import fr.univtours.examplanner.repositories.RoomRepo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.ParseException;
import java.util.List;
import java.util.Objects;

public class RoomController {

    /**
     * Interface avec la base de donnée permettant d'effectuer des opérations standards sur les salles
     */
    @NotNull
    private final RoomRepo repo;

    private static RoomController instance;

    private RoomController() {
        repo = new RoomRepo();
    }

    public static @Nullable RoomDTO getByID( @NotNull String id ) throws ControllerException {
        try {
            return getInstance().repo.getById(id);
        } catch ( RepoException e ) {
            throw new ControllerException("An error occurred during the data fetching.", e);
        }
    }

    public static @Nullable RoomDTO getByPlaces( @NotNull int places ) throws ControllerException {
        try {
            return getInstance().repo.getByPlaces(places);
        } catch ( RepoException e ) {
            throw new ControllerException("An error occurred during the data fetching.", e);
        }
    }

    public static @Nullable List< RoomDTO > getByType( @NotNull String type ) throws ControllerException {
        try {
            return getInstance().repo.getByType(type);
        } catch ( RepoException e ) {
            throw new ControllerException("An error occurred during the data fetching.", e);
        }
    }

    public static @Nullable List< RoomDTO > getByCE( @NotNull String CE ) throws ControllerException {
        try {
            return getInstance().repo.getByCE(CE);
        } catch ( RepoException e ) {
            throw new ControllerException("An error occurred during the data fetching.", e);
        }
    }

    public static @Nullable List< RoomDTO > getByAS( @NotNull String AS ) throws ControllerException {
        try {
            return getInstance().repo.getByAS(AS);
        } catch ( RepoException e ) {
            throw new ControllerException("An error occurred during the data fetching.", e);
        }
    }

    /**
     * Liste les salles
     *
     * @return la liste des salles
     */
    public static @NotNull List< RoomDTO > getAll() throws ControllerException {
        try {
            return getInstance().repo.getAll();
        } catch ( RepoException e ) {
            throw new ControllerException("An error occurred during the data fetching.", e);
        }
    }

    private static RoomController getInstance() {
        if ( Objects.isNull(instance) ) {
            instance = new RoomController();
        }
        return instance;
    }

    public static @NotNull RoomDTO create(
            @NotNull int places,
            @NotNull String type,
            @Nullable List< String > computerEnvironment,
            @NotNull List< String > roomEquipment,
            @Nullable List< SlotDTO > availableSlots
    ) throws ControllerException {
        try {
            return getInstance().repo.save(new RoomDTO(null, places, type,
                    computerEnvironment,
                    roomEquipment,
                    availableSlots
            ));
        } catch ( RepoException | ParseException e ) {
            throw new ControllerException("An error occurred during the data saving.", e);

        }
    }

    /**
     * Modifie une salle
     *
     * @param entity la salle à modifier
     */
    public static void save( @NotNull RoomDTO entity ) throws ControllerException {
        try {
            getInstance().repo.save(entity);
        } catch ( RepoException e ) {
            throw new ControllerException("An error occurred during the data saving.", e);
        }
    }

    /**
     * Supprime une salle
     *
     * @param entity la salle à modifier
     */
    public static void delete( @NotNull RoomDTO entity ) throws ControllerException {
        try {
            getInstance().repo.delete(entity);
        } catch ( RepoException e ) {
            throw new ControllerException("An error occurred during the data deletion.", e);
        }
    }


}
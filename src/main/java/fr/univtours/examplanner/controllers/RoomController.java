package fr.univtours.examplanner.controllers;

import fr.univtours.examplanner.entities.dtos.RoomDTO;
import fr.univtours.examplanner.exceptions.ControllerException;
import fr.univtours.examplanner.repositories.RoomRepo;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RoomController implements BaseController<RoomDTO> {

    // todo(@benoît): Repo

    /**
     * Interface avec la base de donnée permettant d'effectuer des opérations standards sur les salles
     */
    @NotNull
    private final RoomRepo repo;

    public RoomController() {
        repo = new RoomRepo();
    }

    /**
     * Liste les salles
     *
     * @return la liste des salles
     */
    public @NotNull List<RoomDTO> getAll() {
        // TODO implement here
        return new ArrayList<>();
    }

    // todo(@remib18): Spécifier le type des contraintes

    /**
     * Crée une salle
     *
     * @param name        le nom de la salle
     * @param places      le nombre de places disponibles dans la salle
     * @param constraints la liste des contraintes sur la salle (eg: salle machine, vidéo projecteur...)
     * @return la salle créée
     */
    public @NotNull RoomDTO create(@NotNull String name, int places, @NotNull List<Object> constraints) {
        // TODO implement here
        throw new UnsupportedOperationException();
    }

    /**
     * Modifie une salle
     *
     * @param entity la salle à modifier
     */
    public void save( @NotNull RoomDTO entity ) throws ControllerException {
        // TODO implement here
        throw new UnsupportedOperationException();
    }

    /**
     * Supprime une salle
     *
     * @param entity la salle à modifier
     */
    public void delete( @NotNull RoomDTO entity ) throws ControllerException {
        // TODO implement here
        throw new UnsupportedOperationException();
    }


}
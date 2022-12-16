package fr.univtours.examplanner.controllers;

import fr.univtours.examplanner.entities.dtos.ManagerDTO;
import fr.univtours.examplanner.enums.Civility;
import fr.univtours.examplanner.exceptions.ControllerException;
import fr.univtours.examplanner.repositories.ManagerRepo;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ManagerController implements BaseController<ManagerDTO> {

    /**
     * Interface avec la base de données permettant d'effectuer des opérations standards sur les surveillants
     */
    @NotNull
    private final ManagerRepo repo;

    public ManagerController() {
        repo = new ManagerRepo();
    }

    /**
     * Liste les surveillants
     *
     * @return la liste des surveillants
     */
    public @NotNull List<ManagerDTO> getAll() {
        // TODO implement here
        return new ArrayList<>();
    }

    /**
     * Crée un surveillant
     *
     * @param civility  la civilité d'une personne
     * @param lastName  le nom de famille du surveillant
     * @param firstName le prénom du surveillant
     * @return le surveillant créé
     */
    public @NotNull ManagerDTO create(@NotNull Civility civility, @NotNull String lastName, @NotNull String firstName) {
        // TODO implement here
        throw new UnsupportedOperationException();
    }

    /**
     * Modifie un surveillant
     *
     * @param entity le surveillant à modifier
     */
    public void save( @NotNull ManagerDTO entity ) throws ControllerException {
        // TODO implement here
        throw new UnsupportedOperationException();
    }

    /**
     * Supprime un surveillant
     *
     * @param entity le surveillant à supprimer
     */
    public void delete( @NotNull ManagerDTO entity ) throws ControllerException {
        // TODO implement here
        throw new UnsupportedOperationException();
    }


}
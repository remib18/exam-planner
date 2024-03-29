package fr.univtours.examplanner.controllers;

import fr.univtours.examplanner.entities.dtos.ManagerDTO;
import fr.univtours.examplanner.enums.Civility;
import fr.univtours.examplanner.exceptions.ControllerException;
import fr.univtours.examplanner.exceptions.RepoException;
import fr.univtours.examplanner.repositories.ManagerRepo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ManagerController {

    /**
     * Interface avec la base de données permettant d'effectuer des opérations standards sur les surveillants
     */
    @NotNull
    private final ManagerRepo repo;

    private static ManagerController instance;

    private ManagerController() {
        repo = new ManagerRepo();
    }

    /**
     * Liste les surveillants
     *
     * @return la liste des surveillants
     */
    public static @NotNull List< ManagerDTO > getAll() {
        try {
            return getInstance().repo.getAll();
        } catch ( RepoException e ) {
            return new ArrayList<>();
        }
    }

    private static ManagerController getInstance() {
        if ( Objects.isNull(instance) ) {
            instance = new ManagerController();
        }
        return instance;
    }

    public static @Nullable ManagerDTO getByID( String id ) throws ControllerException {
        try {
            return getInstance().repo.getById(id);
        } catch ( RepoException e ) {
            throw new ControllerException("An error occurred during the data fetching.", e);
        }
    }

    /**
     * Crée un surveillant
     *
     * @param civility  la civilité d'une personne
     * @param lastName  le nom de famille du surveillant
     * @param firstName le prénom du surveillant
     * @return le surveillant créé
     */
    public static @NotNull ManagerDTO create(
            @NotNull Civility civility, @NotNull String lastName, @NotNull String firstName
    ) throws ControllerException {
        try {
            return getInstance().repo.save(new ManagerDTO(null, civility, lastName, firstName));
        } catch ( RepoException e ) {
            throw new ControllerException("An error occurred during the data saving.", e);
        }
    }

    /**
     * Modifie un surveillant
     *
     * @param entity le surveillant à modifier
     */
    public static void save( @NotNull ManagerDTO entity ) throws ControllerException {
        try {
            getInstance().repo.save(entity);
        } catch ( RepoException e ) {
            throw new ControllerException("An error occurred during the data saving.", e);
        }
    }

    /**
     * Supprime un surveillant
     *
     * @param entity le surveillant à supprimer
     */
    public static void delete( @NotNull ManagerDTO entity ) throws ControllerException {
        try {
            getInstance().repo.delete(entity);
        } catch ( RepoException e ) {
            throw new ControllerException("An error occurred during the data deletion.", e);
        }
    }


    public static ManagerDTO getByFullName( String lastname, String firstname ) throws RepoException {
        return getInstance().repo.getByFullName(lastname, firstname);

    }
}
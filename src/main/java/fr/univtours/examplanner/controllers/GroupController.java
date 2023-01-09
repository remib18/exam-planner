package fr.univtours.examplanner.controllers;

import fr.univtours.examplanner.entities.dtos.GroupDTO;
import fr.univtours.examplanner.exceptions.ControllerException;
import fr.univtours.examplanner.exceptions.RepoException;
import fr.univtours.examplanner.repositories.GroupRepo;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class GroupController {

    /**
     * Interface avec la base de données permettant d'effectuer des opérations standards sur les groupes
     */
    @NotNull
    private final GroupRepo repo;

    private static GroupController instance;

    private GroupController() {
        repo = new GroupRepo();
    }

    /**
     * Liste les groupes
     *
     * @return la liste des groupes
     */
    public static @NotNull List< GroupDTO > getAll() throws ControllerException {
        try {
            return getInstance().repo.getAll();
        } catch ( RepoException e ) {
            throw new ControllerException("An error occurred during the data fetching.", e);
        }
    }

    private static GroupController getInstance() {
        if ( Objects.isNull(instance) ) {
            instance = new GroupController();
        }
        return instance;
    }

    /**
     * Crée un groupe
     *
     * @param name                              Nom du groupe
     * @param containReducedMobilityPerson      Le groupe contient-il des personnes en situation de handicap
     * @param numberOfStudentsWithoutAdjustment Nombre d'étudiants total du groupe, sans distinction de besoin
     * @param numberOfStudentsWithWritingNeeds  Nombre d'étudiants avec un besoin de secrétaire
     * @param numberOfStudentsWithIsolatedRooms Nombre d'étudiants avec un besoin d'isolation
     * @param numberOfStudentsWithPartTime      Nombre d'étudiants avec un besoin de temps supplémentaires
     * @param subGroupIds                       Liste des groupes enfants
     */
    public static @NotNull GroupDTO create(
            @NotNull String name,
            boolean containReducedMobilityPerson,
            int numberOfStudentsWithoutAdjustment,
            int numberOfStudentsWithWritingNeeds,
            int numberOfStudentsWithIsolatedRooms,
            int numberOfStudentsWithPartTime,
            @NotNull List< String > subGroupIds
    ) throws ControllerException {
        try {
            return getInstance().repo.save(new GroupDTO(
                    null,
                    name,
                    containReducedMobilityPerson,
                    numberOfStudentsWithoutAdjustment,
                    numberOfStudentsWithWritingNeeds,
                    numberOfStudentsWithIsolatedRooms,
                    numberOfStudentsWithPartTime,
                    subGroupIds
            ));
        } catch ( RepoException e ) {
            throw new ControllerException("An error occurred during the data saving.", e);

        }
    }

    /**
     * Modifie un groupe
     *
     * @param entity le groupe à modifier
     */
    public static void save( @NotNull GroupDTO entity ) throws ControllerException {
        try {
            getInstance().repo.save(entity);
        } catch ( RepoException e ) {
            throw new ControllerException("An error occurred during the data saving.", e);
        }
    }

    /**
     * Supprime un groupe
     *
     * @param entity le groupe à supprimer
     */
    public static void delete( @NotNull GroupDTO entity ) throws ControllerException {
        try {
            getInstance().repo.delete(entity);
        } catch ( RepoException e ) {
            throw new ControllerException("An error occurred during the data deletion.", e);
        }
    }


    public static GroupDTO getById( @NotNull String id ) throws ControllerException {
        try {
            return getInstance().repo.getById(id);
        } catch ( RepoException e ) {
            throw new ControllerException("An error occurred during the data fetching.", e);
        }
    }
}
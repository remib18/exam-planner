package fr.univtours.examplanner.controllers;

import fr.univtours.examplanner.entities.dtos.UserDTO;
import fr.univtours.examplanner.enums.UserRole;
import fr.univtours.examplanner.exceptions.ControllerException;
import fr.univtours.examplanner.exceptions.RepoException;
import fr.univtours.examplanner.repositories.UserRepo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class UserController {

    /**
     * Interface avec la base de données permettant d'effectuer des opérations standards sur les utilisateurs
     */
    @NotNull
    private final UserRepo repo;

    private static UserController instance;

    private UserController() {
        super();
        repo = new UserRepo();
    }

    /**
     * Liste les utilisateurs
     *
     * @return la liste des utilisateurs
     */
    public static @NotNull List< UserDTO > getAll() throws ControllerException {
        try {
            return getInstance().repo.getAll();
        } catch ( RepoException e ) {
            throw new ControllerException("An error occurred during the data fetching.", e);
        }
    }

    private static UserController getInstance() {
        if ( Objects.isNull(instance) ) {
            instance = new UserController();
        }
        return instance;
    }

    public static @Nullable UserDTO getByID( @NotNull String id ) throws ControllerException {
        try {
            return getInstance().repo.getById(id);
        } catch ( RepoException e ) {
            throw new ControllerException("An error occurred during the data fetching.", e);
        }
    }

    public static @Nullable UserDTO getByEmail( @NotNull String email ) throws ControllerException {
        try {
            return getInstance().repo.getByMail(email);
        } catch ( RepoException e ) {
            throw new ControllerException("An error occurred during the data fetching.", e);
        }
    }

    /**
     * Crée un utilisateur
     *
     * @param mail                  le nom de l'utilisateur
     * @param password              le mot de passe de l'utilisateur
     * @param role                  le rôle de l'utilisateur
     * @param departmentOrManagerID l'identifiant du département ou du manager lié à l'utilisateur s'il en a un
     * @return l'utilisateur créé
     */
    public static @NotNull UserDTO create(
            @NotNull String mail,
            @NotNull String password,
            @NotNull UserRole role,
            @Nullable String departmentOrManagerID
    ) throws ControllerException {
        try {
            return getInstance().repo.save(new UserDTO(
                    null,
                    mail,
                    password,
                    role.equals(UserRole.Department) ? departmentOrManagerID : null,
                    role.equals(UserRole.Manager) ? departmentOrManagerID : null
            ));
        } catch ( RepoException e ) {
            throw new ControllerException("An error occurred during the data saving.", e);
        }
    }

    /**
     * Modifie ou crée un utilisateur
     *
     * @param entity l'utilisateur à modifier
     */
    public static void save( @NotNull UserDTO entity ) throws ControllerException {
        try {
            getInstance().repo.save(entity);
        } catch ( RepoException e ) {
            throw new ControllerException("An error occurred during the data saving.", e);
        }
    }

    /**
     * Supprime un utilisateur
     *
     * @param entity l'utilisateur à supprimer
     */
    public static void delete( @NotNull UserDTO entity ) throws ControllerException {
        try {
            getInstance().repo.delete(entity);
        } catch ( RepoException e ) {
            throw new ControllerException("An error occurred during the data deletion.", e);
        }
    }


}
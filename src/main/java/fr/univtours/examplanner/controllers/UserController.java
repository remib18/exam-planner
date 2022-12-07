package fr.univtours.examplanner.controllers;

import fr.univtours.examplanner.entities.dtos.UserDTO;
import fr.univtours.examplanner.enums.UserRole;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class UserController implements BaseController<UserDTO> {

    // todo(@benoît): Repo
    /**
     * Interface avec la base de données permettant d'effectuer des opérations standards sur les utilisateurs
     */
    @NotNull
    private final Object repo;

    public UserController() {
        repo = new Object();
    }

    /**
     * Liste les utilisateurs
     *
     * @return la liste des utilisateurs
     */
    public @NotNull List<UserDTO> getAll() {
        // TODO implement here
        return new ArrayList<>();
    }


    /**
     * Crée un utilisateur
     *
     * @param mail           le nom de l'utilisateur
     * @param departmentName le nom du département auquel il appartient
     * @param role           le rôle de l'utilisateur
     * @return l'utilisateur créé
     */
    public @NotNull UserDTO create(@NotNull String mail, @NotNull String departmentName, @NotNull UserRole role) {
        // TODO implement here
        throw new UnsupportedOperationException();
    }

    /**
     * Modifie un utilisateur
     *
     * @param entity l'utilisateur à modifier
     */
    public void save(@NotNull UserDTO entity) throws Exception {
        // TODO implement here
        throw new Exception();
    }

    /**
     * Supprime un utilisateur
     *
     * @param entity l'utilisateur à supprimer
     */
    public void delete(@NotNull UserDTO entity) throws Exception {
        // TODO implement here
        throw new Exception();
    }


}
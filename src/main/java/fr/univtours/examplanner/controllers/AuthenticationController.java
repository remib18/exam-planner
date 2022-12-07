package fr.univtours.examplanner.controllers;

import fr.univtours.examplanner.entities.dtos.UserDTO;
import org.jetbrains.annotations.NotNull;

public class AuthenticationController {

    /**
     * Interface à la base de donnée permettant d'effectuer des opérations standards sur les utilisateurs
     */
    @NotNull
    private final UserController userController;

    public AuthenticationController() {
        userController = new UserController();
    }

    /**
     * Permet de se connecter
     *
     * @param login    identifiant de l'utilisateur
     * @param password mot de passe de l'utilisateur
     * @return vrai si les informations de connexions sont correctes et faux sinon
     */
    public boolean login(String login, String password) {
        // TODO implement here
        return false;
    }

    // todo(@mya): Specify type for filter

    /**
     * Vérifie si l'utilisateur a l'autorisation d'accéder à la ressource
     *
     * @param user   utilisateur demandant un accès
     * @param filter condition d'accès
     * @return vrai si l'utilisateur est autorisé et faux sinon
     */
    public boolean checkAccessRights(@NotNull UserDTO user, Object filter) {
        // TODO implement here
        return false;
    }

}
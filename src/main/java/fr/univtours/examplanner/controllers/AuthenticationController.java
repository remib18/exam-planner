package fr.univtours.examplanner.controllers;

import fr.univtours.examplanner.Storage;
import fr.univtours.examplanner.entities.dtos.UserDTO;
import fr.univtours.examplanner.enums.Scenes;
import fr.univtours.examplanner.enums.UserRole;
import fr.univtours.examplanner.utils.Database;
import org.jetbrains.annotations.NotNull;

public class AuthenticationController {

    /**
     * Interface à la base de donnée permettant d'effectuer des opérations standards sur les utilisateurs
     */
    @NotNull
    //private final UserController userController;

    public AuthenticationController() {
        //userController = new UserController();
    }

    /**
     * Permet de se connecter
     *
     * @param login    identifiant de l'utilisateur
     * @param password mot de passe de l'utilisateur
     * @return vrai si les informations de connexions sont correctes et faux sinon
     */
    public static boolean login( String login, String password ) {
        // TODO implement here
        // Temporary code for testing
        Storage.setUser(new UserDTO(Database.getNewUUID(), "testMail", "testPassword", null, null));
        return false;
    }

    public static void logout() {
        Storage.setUser(null);
        Storage.setScene(Scenes.Login);
    }

    /**
     * Vérifie si l'utilisateur courant possède l'autorisation d'accéder à la ressource
     *
     * @param filter condition d'accès
     * @return vrai si l'utilisateur est autorisé et faux sinon
     */
    public static boolean checkAccessRights( UserRole filter ) {
        // TODO implement here
        return true;
    }

    public static String encryptPassword( String password ) {
        // TODO implement here
        return password;
    }

    private static boolean checkPassword( String password, String encryptedPassword ) {
        // TODO implement here
        return password.equals(encryptedPassword);
    }

}
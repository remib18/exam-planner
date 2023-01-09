package fr.univtours.examplanner.controllers;

import fr.univtours.examplanner.Storage;
import fr.univtours.examplanner.entities.dtos.UserDTO;
import fr.univtours.examplanner.enums.Scenes;
import fr.univtours.examplanner.enums.UserRole;
import fr.univtours.examplanner.exceptions.ControllerException;
import fr.univtours.examplanner.ui.PopupController;
import fr.univtours.examplanner.ui.controllers.popups.InvalidCredentialsPopupController;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class AuthenticationController {

    private AuthenticationController() {
        super();
        //userController = new UserController();
    }

    /**
     * Permet de se connecter
     *
     * @param login    identifiant de l'utilisateur
     * @param password mot de passe de l'utilisateur
     * @return vrai si les informations de connexions sont correctes et faux sinon
     */
    public static void login( String login, String password ) {
        try {
            if ( Objects.isNull(login) || Objects.isNull(password) || login.isEmpty() || password.isEmpty() ) {
                openErrorPopup();
                return;
            }
            UserDTO user = UserController.getByEmail(login);
            if ( Objects.nonNull(user) && checkPassword(password, user.getPassword()) ) {
                Storage.setUser(user);
                Storage.setScene(Scenes.Dashboard);
                return;
            }
            openErrorPopup();
        } catch ( ControllerException e ) {
            throw new RuntimeException("An error occurred during the data fetching.", e);
        }
    }

    /**
     * Ouvre un popup d'erreur
     */
    private static void openErrorPopup() {
        PopupController popup = new PopupController(
                "popups.invalid-credentials.title",
                "popups/invalid-credentials.fxml",
                new InvalidCredentialsPopupController()
        );
        popup.open();
    }

    /**
     * Vérifie si un mot de passe est valide
     *
     * @param password          mot de passe à vérifier
     * @param encryptedPassword mot de passe crypté
     * @return vrai si le mot de passe est valide et faux sinon
     */
    private static boolean checkPassword( @NotNull String password, @Nullable String encryptedPassword ) {
        String encrypted = encryptPassword(password);
        // System.out.println(encrypted);
        return encrypted.equals(encryptedPassword);
    }

    /**
     * Crypte un mot de passe
     *
     * @param password mot de passe à crypter
     * @return mot de passe crypté
     */
    public static @NotNull String encryptPassword( @NotNull String password ) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

            BigInteger no = new BigInteger(1, bytes);

            StringBuilder encrypted = new StringBuilder(no.toString(16));

            while ( 32 > encrypted.length() ) {
                encrypted.insert(0, "0");
            }

            return encrypted.toString();
        } catch ( NoSuchAlgorithmException e ) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Permet de se déconnecter
     */
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
        UserDTO user = Storage.getUser();
        if ( Objects.isNull(user) ) {
            return false;
        }
        return user.getRole().equals(filter);
    }

}
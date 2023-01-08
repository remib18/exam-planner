package fr.univtours.examplanner;

import fr.univtours.examplanner.entities.dtos.UserDTO;
import fr.univtours.examplanner.enums.Scenes;
import fr.univtours.examplanner.translations.SupportedLanguages;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

// Todo(@remib18): fix warning here

/**
 * Permet de stocker les données de l'application à travers les différentes vues
 */
public class Storage {

	/**
	 * L'instance (singleton)
	 */
    private static @Nullable Storage instance;

    /**
     * La scène actuelle
     */
    private final @NotNull ObjectProperty< Scenes > scene = new SimpleObjectProperty<>();

    /**
     * La langue actuelle
     */
    private final @NotNull ObjectProperty< SupportedLanguages > language = new SimpleObjectProperty<>();

    /**
     * L'utilisateur courant<br> {@code null} si aucun utilisateur n'est connecté
     *
     * @see UserDTO
     */
    private final @NotNull ObjectProperty< UserDTO > user = new SimpleObjectProperty<>(null);

    private Storage() {
    }

    public static @NotNull Scenes getScene() {
        return getInstance().scene.get();
    }

    private static @NotNull Storage getInstance() {
        if ( null == instance ) {
            instance = new Storage();
        }
        return instance;
    }

    public static void setScene( @NotNull Scenes currentScene ) {
        getInstance().scene.set(currentScene);
    }

    public static @NotNull ObjectProperty< Scenes > sceneProperty() {
        return getInstance().scene;
    }

    public static SupportedLanguages getLanguage() {
        return getInstance().language.get();
    }

    public static void setLanguage( @NotNull SupportedLanguages currentLanguage ) {
        getInstance().language.set(currentLanguage);
    }

    public static @NotNull ObjectProperty< SupportedLanguages > languageProperty() {
        return getInstance().language;
    }

    public static @Nullable UserDTO getUser() {
        return getInstance().user.get();
    }

    public static void setUser( @Nullable UserDTO user ) {
        getInstance().user.set(user);
    }

    public static @NotNull ObjectProperty< UserDTO > userProperty() {
        return getInstance().user;
    }

}

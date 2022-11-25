package fr.univtours.examplanner;

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

	private static @Nullable Storage instance;

	private final @NotNull ObjectProperty<Scenes> currentScene = new SimpleObjectProperty<>();

	private final @NotNull ObjectProperty<SupportedLanguages> currentLanguage = new SimpleObjectProperty<>();

	private Storage() {
	}

	private static @NotNull Storage getInstance() {
		if (instance == null) {
			instance = new Storage();
		}
		return instance;
	}

	public static Scenes getCurrentScene() {
		return getInstance().currentScene.get();
	}

	public static void setCurrentScene(@NotNull Scenes currentScene) {
		getInstance().currentScene.set(currentScene);
	}

	public static @NotNull ObjectProperty<Scenes> currentSceneProperty() {
		return getInstance().currentScene;
	}

	public static SupportedLanguages getCurrentLanguage() {
		return getInstance().currentLanguage.get();
	}

	public static void setCurrentLanguage(@NotNull SupportedLanguages currentLanguage) {
		getInstance().currentLanguage.set(currentLanguage);
	}

	public static @NotNull ObjectProperty<SupportedLanguages> currentLanguageProperty() {
		return getInstance().currentLanguage;
	}

}

package fr.univtours.examplanner;

import fr.univtours.examplanner.enums.Scenes;
import fr.univtours.examplanner.translations.SupportedLanguages;
import fr.univtours.examplanner.translations.Translation;
import fr.univtours.examplanner.ui.SceneController;
import javafx.application.Application;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

public class App extends Application {
	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(@NotNull Stage stage) {
		Translation.setLanguage(SupportedLanguages.French);

		Storage.currentSceneProperty().addListener((observable, oldValue, newValue) -> {
			stage.setScene(SceneController.getScene(newValue));
			stage.setTitle(Translation.get(SceneController.getSceneTitle(newValue)));
		});

		Storage.setCurrentScene(Scenes.Login);
		stage.show();
	}
}
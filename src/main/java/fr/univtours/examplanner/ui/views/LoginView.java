package fr.univtours.examplanner.ui.views;

import fr.univtours.examplanner.ui.controllers.LoginViewController;
import fr.univtours.examplanner.utils.Ressource;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class LoginView {

	public static final String TITLE = "app.title.login";

	public static @NotNull Scene getScene() throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(Ressource.resolve("views/login.fxml"));
		fxmlLoader.setController(new LoginViewController());
		return new Scene(fxmlLoader.load(), 600, 400);
	}

}
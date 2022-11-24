package fr.univtours.examplanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class App extends Application {
	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) {
		Scene scene = new Scene(new Label("Exam Planner"), 320, 240);
		stage.setTitle("Exam Planner!");
		stage.setScene(scene);
		stage.show();
	}
}
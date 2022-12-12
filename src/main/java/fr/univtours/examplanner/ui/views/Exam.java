package fr.univtours.examplanner.ui.views;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import org.jetbrains.annotations.NotNull;

public class Exam {

	public static final String TITLE = "app.title.exam";

	public static @NotNull Scene getScene() {
		return new Scene(new Label(TITLE), 320, 240);
	}

}
package fr.univtours.examplanner.ui;

import fr.univtours.examplanner.enums.Scenes;
import fr.univtours.examplanner.ui.views.*;
import javafx.scene.Scene;
import org.jetbrains.annotations.NotNull;

public class SceneController {

	/**
	 * Obtiens la scène correspondante
	 *
	 * @param scene La scène à obtenir
	 * @return La scène correspondante
	 */
	public static @NotNull Scene getScene(@NotNull Scenes scene) {
		return switch (scene) {
			case Dashboard -> Dashboard.getScene();
			case Department -> Department.getScene();
			case Exam -> Exam.getScene();
			case Group -> Group.getScene();
			case Login -> Login.getScene();
			case Manager -> Manager.getScene();
			case Room -> Room.getScene();
			case Slot -> Slot.getScene();
			case User -> User.getScene();
			default -> throw new IllegalArgumentException("Unknown scene: " + scene);
		};
	}

	/**
	 * Obtiens le titre de la scène correspondant
	 *
	 * @param scene La scène à obtenir
	 * @return Le titre de la scène correspondante
	 */
	public static @NotNull String getSceneTitle(@NotNull Scenes scene) {
		return switch (scene) {
			case Dashboard -> Dashboard.TITLE;
			case Department -> Department.TITLE;
			case Exam -> Exam.TITLE;
			case Group -> Group.TITLE;
			case Login -> Login.TITLE;
			case Manager -> Manager.TITLE;
			case Room -> Room.TITLE;
			case Slot -> Slot.TITLE;
			case User -> User.TITLE;
			default -> throw new IllegalArgumentException("Unknown scene: " + scene);
		};
	}

}

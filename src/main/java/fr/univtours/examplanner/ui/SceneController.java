package fr.univtours.examplanner.ui;

import fr.univtours.examplanner.enums.Scenes;
import fr.univtours.examplanner.ui.views.*;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class SceneController {

	/**
	 * Obtiens la scène correspondante
	 *
	 * @param scene La scène à obtenir
	 * @return La scène correspondante
	 */
	public static @NotNull Scene getScene(@NotNull Scenes scene) {
		try {
			return switch (scene) {
				case Dashboard -> DashboardView.getScene();
				case Department -> DepartmentView.getScene();
				case Exam -> ExamView.getScene();
				case Group -> GroupView.getScene();
				case Login -> LoginView.getScene();
				case Manager -> ManagerView.getScene();
				case Room -> RoomView.getScene();
				case Slot -> SlotView.getScene();
				case User -> UserView.getScene();
			};
		} catch (IOException e) {
			e.printStackTrace();
			return new Scene(new Label("Erreur lors du chargement de la scène"));
		}
	}

	/**
	 * Obtiens le titre de la scène correspondant
	 *
	 * @param scene La scène à obtenir
	 * @return Le titre de la scène correspondante
	 */
	public static @NotNull String getSceneTitle(@NotNull Scenes scene) {
		return switch (scene) {
			case Dashboard -> DashboardView.TITLE;
			case Department -> DepartmentView.TITLE;
			case Exam -> ExamView.TITLE;
			case Group -> GroupView.TITLE;
			case Login -> LoginView.TITLE;
			case Manager -> ManagerView.TITLE;
			case Room -> RoomView.TITLE;
			case Slot -> SlotView.TITLE;
			case User -> UserView.TITLE;
		};
	}

}

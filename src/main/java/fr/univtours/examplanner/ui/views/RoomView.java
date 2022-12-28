package fr.univtours.examplanner.ui.views;

import fr.univtours.examplanner.ui.controllers.RoomViewController;
import fr.univtours.examplanner.utils.Ressource;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public enum RoomView {
    ;

    public static final String TITLE = "app.title.room";

    public static @NotNull Scene getScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Ressource.resolve("views/rooms.fxml"));
        fxmlLoader.setController(new RoomViewController());
        return new Scene(fxmlLoader.load());
    }
}
package fr.univtours.examplanner.ui.views;

import fr.univtours.examplanner.ui.controllers.DashboardViewController;
import fr.univtours.examplanner.utils.Ressource;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public enum DashboardView {
    ;

    public static final String TITLE = "app.title.dashboard";

    public static @NotNull Scene getScene() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Ressource.resolve("views/dashboard.fxml"));
        fxmlLoader.setController(new DashboardViewController());
        return new Scene(fxmlLoader.load());
    }

}
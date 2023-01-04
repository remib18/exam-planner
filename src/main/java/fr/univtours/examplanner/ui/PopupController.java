package fr.univtours.examplanner.ui;

import fr.univtours.examplanner.utils.Ressource;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class PopupController {

    private static PopupController instance;


    private Stage stage;

    public PopupController( String title, String fxmlPath, Object controller ) {
        super();
        FXMLLoader loader = new FXMLLoader(Ressource.resolve(fxmlPath));
        loader.setController(controller);
        try {
            Scene scene = new Scene(loader.load());
            initialize(title, scene);
        } catch ( IOException e ) {
            throw new RuntimeException(e);
        }
    }

    private void initialize( String title, Scene scene ) {
        stage = new Stage();
        stage.setResizable(false);
        stage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        stage.setScene(scene);

        instance = this;
    }

    public PopupController( String title, Scene scene ) {
        super();
        initialize(title, scene);
    }

    public static void close() {
        if ( Objects.nonNull(instance) ) {
            instance.stage.close();
        }
    }

    public void open() {
        stage.showAndWait();
    }

}

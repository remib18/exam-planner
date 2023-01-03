package fr.univtours.examplanner.ui.components;

import fr.univtours.examplanner.Storage;
import fr.univtours.examplanner.translations.Translation;
import fr.univtours.examplanner.utils.Ressource;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.concurrent.Callable;

public class DashboardTile extends VBox {

    @FXML
    private ImageView icon;

    @FXML
    private Text title;

    private String text = "";

    private Callable< Void > onClick;

    public DashboardTile( String title, String imagePath ) throws IOException {
        super();
        loadFXML();

        setTitle(title);
        setIcon(imagePath);
    }

    private void loadFXML() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Ressource.resolve("components/DashboardTile.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
    }

    public void setTitle( String title ) {
        if ( !Objects.isNull(title) ) {
            this.text = title;
        }
        if ( Objects.isNull(this.text) || Objects.isNull(this.title) ) return;
        this.title.textProperty().set(Translation.get(title));
    }

    private void setIcon( String imagePath ) {
        InputStream image = Ressource.getStream(imagePath);
        this.icon.imageProperty().set(new Image(image));
    }

    @FXML
    protected void handleClick() {
        if ( Objects.isNull(onClick) ) return;
        try {
            onClick.call();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public void setOnClick( Callable< Void > onClick ) {
        this.onClick = onClick;
    }

    @FXML
    private void initialize() {

        Storage.languageProperty().addListener(( observable, oldValue, newValue ) -> {
            onLanguageUpdate();
        });
        onLanguageUpdate();
    }

    private void onLanguageUpdate() {
        if ( Objects.isNull(text) ) return;
        setTitle(text);
    }
}

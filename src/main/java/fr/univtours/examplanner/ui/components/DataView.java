package fr.univtours.examplanner.ui.components;

import fr.univtours.examplanner.Storage;
import fr.univtours.examplanner.entities.EditableEntity;
import fr.univtours.examplanner.enums.Scenes;
import fr.univtours.examplanner.translations.Translation;
import fr.univtours.examplanner.utils.Ressource;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class DataView< T extends EditableEntity > extends AnchorPane implements Initializable {

    private String title;

    @FXML
    private ScrollPane scrollContainer;

    @FXML
    private Text headerTitle;

    @FXML
    private ImageView icon;

    /**
     * Create a new Data view interface
     *
     * @param headerIconPath The path to the icon to display in the header
     * @param headerTitle    The title to display in the header
     * @throws IOException If the FXML file cannot be loaded
     */
    public DataView(
            String headerIconPath, String headerTitle
    ) throws IOException {
        this(headerIconPath, headerTitle, null);
    }

    /**
     * Create a new Data view interface
     *
     * @param headerIconPath The path to the icon to display in the header
     * @param headerTitle    The title to display in the header
     * @param table          The table to display in the view
     * @throws IOException If the FXML file cannot be loaded
     */
    public DataView(
            String headerIconPath, String headerTitle, DataTable< T > table
    ) throws IOException {
        super();
        loadFXML();

        setTitle(headerTitle);
        setIcon(headerIconPath);
        setTable(table);
    }

    private void loadFXML() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Ressource.resolve("components/DataView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
    }

    public void setTitle( String title ) {
        if ( !Objects.isNull(title) ) {
            this.title = title;
        }
        if ( Objects.isNull(this.title) || Objects.isNull(this.headerTitle) ) return;
        this.headerTitle.textProperty().set(Translation.get(title));
    }

    private void setIcon( String iconPath ) {
        InputStream image = Ressource.getStream(iconPath);
        this.icon.imageProperty().set(new Image(image));
    }

    public void setTable( DataTable< T > table ) {
        this.scrollContainer.setContent(table);
    }

    @FXML
    private void handleBackNavigation() {
        Storage.setScene(Scenes.Dashboard);
    }

    @Override
    public void initialize( URL location, ResourceBundle resources ) {
        Storage.languageProperty().addListener(( observable, oldValue, newValue ) -> {
            onLanguageUpdate();
        });
        onLanguageUpdate();
    }

    private void onLanguageUpdate() {
        if ( Objects.isNull(title) ) return;
        setTitle(title);
    }
}

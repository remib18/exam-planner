package fr.univtours.examplanner.ui.components;

import fr.univtours.examplanner.Storage;
import fr.univtours.examplanner.entities.EditableEntity;
import fr.univtours.examplanner.enums.Scenes;
import fr.univtours.examplanner.translations.Translation;
import fr.univtours.examplanner.utils.Ressource;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.Callable;
import java.util.function.Consumer;

public class DataView< T extends EditableEntity > extends AnchorPane implements Initializable {

    private String title;

    private final SimpleObjectProperty< @Nullable T > addedEntity = new SimpleObjectProperty<>(null);

    private Callable< T > onAddRequest;

    private Runnable onDeleteRequest;

    @FXML
    private ScrollPane scrollContainer;

    @FXML
    private Text headerTitle;

    @FXML
    private ImageView icon;

    private Consumer< T > onSaveRequest;

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

    public DataTable< T > getTable() {
        return (DataTable< T >) this.scrollContainer.getContent();
    }

    public void setTable( DataTable< T > table ) {
        this.scrollContainer.setContent(table);
    }

    @FXML
    protected void handleAdd() {
        if ( Objects.isNull(onAddRequest) || Objects.isNull(onSaveRequest) ) return;
        try {
            if ( Objects.isNull(addedEntity.get()) ) {
                TreeItem< T > root = getTable().getRoot();
                addedEntity.set(onAddRequest.call());
                root.getChildren().add(new TreeItem<>(addedEntity.get()));
                return;
            }
            onSaveRequest.andThen(t -> {
                addedEntity.set(null);
                getTable().refresh();
            }).accept(addedEntity.get());
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleDelete() {
        if ( Objects.isNull(onDeleteRequest) ) return;
        try {
            onDeleteRequest.run();
            getTable().refresh();
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public void setOnAddRequest( Callable< T > handler ) {
        this.onAddRequest = handler;
    }

    public void setOnSaveRequest( Consumer< T > handler ) {
        this.onSaveRequest = handler;
    }

    public void setOnDeleteRequest( Runnable handler ) {
        this.onDeleteRequest = handler;
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

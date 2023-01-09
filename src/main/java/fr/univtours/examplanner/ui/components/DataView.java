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

/**
 * Un composant graphique reprenant un affichage complet destiné à la gestion d'une entité
 *
 * @param <T> Le type d'entité géré
 */
public class DataView< T extends EditableEntity > extends AnchorPane implements Initializable {

    /**
     * L'entité gérée par le composant
     */
    private final SimpleObjectProperty< @Nullable T > addedEntity = new SimpleObjectProperty<>(null);

    /**
     * Le titre affiché dans le header
     */
    private String title;

    /**
     * Appelé lorsque l'utilisateur souhaite créer une nouvelle entitée
     */
    private Callable< T > onAddRequest;

    /**
     * Appelé lorsque l'utilisateur souhaite supprimer la sélection
     */
    private Runnable onDeleteRequest;

    /**
     * Le conteneur du tableau de données
     */
    @FXML
    private ScrollPane scrollContainer;

    /**
     * Le composant JavaFX contenant le titre du header
     */
    @FXML
    private Text headerTitle;

    /**
     * Le composant JavaFX contenant l'icône du header
     */
    @FXML
    private ImageView icon;

    /**
     * Appelé lorsque l'utilisateur souhaite sauvegarder l'ajout d'une entité
     */
    private Consumer< T > onSaveRequest;

    /**
     * Crée un nouveau composant de gestion d'entité
     *
     * @param headerIconPath Le chemin vers l'icône du header
     * @param headerTitle    Le titre du header
     * @throws IOException Si le fichier FXML n'a pas pu être chargé
     */
    public DataView(
            String headerIconPath, String headerTitle
    ) throws IOException {
        this(headerIconPath, headerTitle, null);
    }

    /**
     * Crée un nouveau composant de gestion d'entité
     *
     * @param headerIconPath Le chemin vers l'icône du header
     * @param headerTitle    Le titre du header
     * @param table          Le tableau de données
     * @throws IOException   Si le fichier FXML n'a pas pu être chargé
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

    /**
     * Charge le fichier FXML
     *
     * @throws IOException Si le fichier FXML n'a pas pu être chargé
     */
    private void loadFXML() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Ressource.resolve("components/DataView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.load();
    }

    /**
     * Définit le titre du header
     *
     * @param title Le titre du header
     */
    public void setTitle( String title ) {
        if ( !Objects.isNull(title) ) {
            this.title = title;
        }
        if ( Objects.isNull(this.title) || Objects.isNull(this.headerTitle) ) return;
        this.headerTitle.textProperty().set(Translation.get(title));
    }

    /**
     * Définit l'icône du header
     *
     * @param iconPath Le chemin vers l'icône du header
     */
    private void setIcon( String iconPath ) {
        InputStream image = Ressource.getStream(iconPath);
        this.icon.imageProperty().set(new Image(image));
    }

    /**
     * Appelé quand l'utilisateur clique sur le bouton d'ajout
     *
     * @see #onAddRequest
     */
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

    /**
     * @return la table de données
     */
    public DataTable< T > getTable() {
        return (DataTable< T >) this.scrollContainer.getContent();
    }

    /**
     * Définit la table de données
     *
     * @param table La table de données
     */
    public void setTable( DataTable< T > table ) {
        this.scrollContainer.setContent(table);
    }

    /**
     * Appelé quand l'utilisateur clique sur le bouton de suppression
     *
     * @see #onDeleteRequest
     */
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

    /**
     * Définit l'action à effectuer lorsque l'utilisateur souhaite créer une nouvelle entité L'action doit retourner
     * l'entité créée avec des valeurs par défaut
     *
     * @param handler L'action à effectuer
     */
    public void setOnAddRequest( Callable< T > handler ) {
        this.onAddRequest = handler;
    }

    /**
     * Définit l'action à effectuer lorsque l'utilisateur souhaite enregistrer une entité Supprime l'entité passée en
     * paramètre
     *
     * @param handler L'action à effectuer
     */
    public void setOnSaveRequest( Consumer< T > handler ) {
        this.onSaveRequest = handler;
    }

    /**
     * Définit l'action à effectuer lorsque l'utilisateur souhaite supprimer des entités Supprime les entités passées en
     * paramètre
     *
     * @param handler L'action à effectuer
     */
    public void setOnDeleteRequest( Runnable handler ) {
        this.onDeleteRequest = handler;
    }

    /**
     * Appelé quand l'utilisateur clique sur le bouton de retour
     */
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

    @FXML
    private Text backText;

    @FXML
    private Text deleteText;

    @FXML
    private Text addText;

    private void onLanguageUpdate() {
        if ( Objects.nonNull(title) ) {
            setTitle(title);
        }
        backText.setText(Translation.get("actions.back"));
        deleteText.setText(Translation.get("actions.delete"));
        addText.setText(Translation.get("actions.add"));
    }
}

package fr.univtours.examplanner.ui.components;

import fr.univtours.examplanner.entities.EditableEntity;
import fr.univtours.examplanner.utils.Ressource;
import fr.univtours.examplanner.utils.TableColumnDeclaration;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeSortMode;
import javafx.scene.control.TreeTableView;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * Un composant graphique représentant une table de données.<br> On notera l'utilisation d'une {@code TreeTableView}
 * pour permettre l'affichage de données hiérarchiques ou non
 *
 * @param <T> Le type d'entité à afficher
 */
public class DataTable< T extends EditableEntity > extends TreeTableView< T > {

    private DataTable() {super();}

    private DataTable( TreeItem< T > root ) {super();}

    private Callable< TreeItem< T > > reloadRoot;

    /**
     * Crée une nouvelle table de données
     *
     * @param columns    Les déclarations des colonnes à afficher
     * @param reloadRoot Le callable permettant de recharger les données à afficher
     */
    public DataTable( List< TableColumnDeclaration< T, ? > > columns, Callable< TreeItem< T > > reloadRoot ) {
        super();
        setEditable(true);
        setSortMode(TreeSortMode.ONLY_FIRST_LEVEL);
        getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        setShowRoot(false);
        getStylesheets().add(Ressource.resolve("styles/table.css").toExternalForm());
        getStyleClass().add("table-view");

        buildColumns(columns);
        this.reloadRoot = reloadRoot;
        refresh();
    }

    /**
     * Construit les colonnes de la table
     *
     * @param columns Les déclarations des colonnes à afficher
     */
    private void buildColumns( List< TableColumnDeclaration< T, ? > > columns ) {
        columns.forEach(col -> getColumns().add(col.build()));
    }

    /**
     * Actualise les données affichées
     *
     * @see #reloadRoot
     */
    public void refresh() {
        try {
            setRoot(reloadRoot.call());
        } catch ( Exception e ) {
            throw new RuntimeException(e);
        }
    }

}

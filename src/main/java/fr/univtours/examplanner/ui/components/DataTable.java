package fr.univtours.examplanner.ui.components;

import fr.univtours.examplanner.entities.EditableEntity;
import fr.univtours.examplanner.utils.Ressource;
import fr.univtours.examplanner.utils.TableColumnDeclaration;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeSortMode;
import javafx.scene.control.TreeTableView;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * A data table is a table view that can be used to display data
 *
 * @param <T> The type of the data to display
 */
public class DataTable< T extends EditableEntity > extends TreeTableView< T > {

    private DataTable() {super();}

    private DataTable( TreeItem< T > root ) {super();}

    private Callable< TreeItem< T > > reloadRoot;

    /**
     * Create a new data table
     *
     * @param columns    The columns to display
     * @param reloadRoot The callback to call in order to get all the data
     */
    public DataTable( List< TableColumnDeclaration< T, ? > > columns, Callable< TreeItem< T > > reloadRoot ) {
        super();
        setEditable(true);
        setSortMode(TreeSortMode.ONLY_FIRST_LEVEL);
        setShowRoot(false);
        getStylesheets().add(Ressource.resolve("styles/table.css").toExternalForm());
        getStyleClass().add("table-view");

        buildColumns(columns);
        this.reloadRoot = reloadRoot;
        refresh();
    }

    public void refresh() {
        try {
            setRoot(reloadRoot.call());
        } catch ( Exception e ) {
            throw new RuntimeException(e);
        }
    }


    public final TreeItem< T > _getRoot() {
        return super.getRoot();
    }

    private void buildColumns( List< TableColumnDeclaration< T, ? > > columns ) {
        columns.forEach(col -> getColumns().add(col.build()));
    }

}

package fr.univtours.examplanner.ui.components;

import fr.univtours.examplanner.entities.EditableEntity;
import fr.univtours.examplanner.utils.Ressource;
import fr.univtours.examplanner.utils.TableColumnDeclaration;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeSortMode;
import javafx.scene.control.TreeTableView;

import java.util.List;

/**
 * A data table is a table view that can be used to display data
 *
 * @param <T> The type of the data to display
 */
public class DataTable< T extends EditableEntity > extends TreeTableView< T > {

    private DataTable() {super();}

    private DataTable( TreeItem< T > root ) {super();}

    /**
     * Create a new data table
     *
     * @param columns The columns to display
     * @param root    The root of the tree
     */
    public DataTable( List< TableColumnDeclaration< T, ? > > columns, TreeItem< T > root ) {
        super();
        setEditable(true);
        setSortMode(TreeSortMode.ONLY_FIRST_LEVEL);
        setShowRoot(false);
        getStylesheets().add(Ressource.resolve("styles/table.css").toExternalForm());
        getStyleClass().add("table-view");

        buildColumns(columns);
        setRoot(root);
    }

    private void buildColumns( List< TableColumnDeclaration< T, ? > > columns ) {
        columns.forEach(col -> getColumns().add(col.build()));
    }

}

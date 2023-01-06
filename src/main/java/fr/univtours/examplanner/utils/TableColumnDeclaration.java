package fr.univtours.examplanner.utils;

import fr.univtours.examplanner.Storage;
import fr.univtours.examplanner.entities.EditableEntity;
import fr.univtours.examplanner.exceptions.ControllerException;
import fr.univtours.examplanner.translations.Translation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.ComboBoxTreeTableCell;
import javafx.scene.control.cell.TextFieldTreeTableCell;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;

import java.util.List;
import java.util.Objects;

/**
 * Déclaration d'une colonne d'une {@code DataTable}
 *
 * @param property     La propriété gérée par la colonne. L'entité {@code T} doit posséder une propriété de ce nom (avec
 *                     un getter et un setter)
 * @param title        Le titre de la colonne
 * @param editable     Indique si la colonne est éditable
 * @param onEditCommit L'action à effectuer lors de la validation d'une modification
 * @param converter    Le convertisseur de type de la colonne
 * @param type         Le type de la colonne (texte, liste déroulante, etc.)
 * @param options      Les options de la liste déroulante
 * @param <DTO>        Le type de l'entité
 * @param <T>          Le type de la propriété
 * @see fr.univtours.examplanner.utils.TableColumnDeclaration.Type
 * @see fr.univtours.examplanner.ui.components.DataTable
 */
public record TableColumnDeclaration< DTO extends EditableEntity, T >(String property, String title, boolean editable,
                                                                      EventHandler< TreeTableColumn.CellEditEvent< DTO, T > > onEditCommit,
                                                                      StringConverter< T > converter, Type type,
                                                                      List< T > options) {

    /**
     * Déclaration d'une colonne d'une table
     *
     * @param property La propriété gérée par la colonne. L'entité {@code T} doit posséder une propriété de ce nom (avec
     *                 un getter et un setter)
     * @param title    Le titre de la colonne
     * @param editable Indique si la colonne est éditable
     */
    public TableColumnDeclaration( String property, String title, boolean editable ) {
        this(property, title, editable, null, (StringConverter< T >) new DefaultStringConverter(), Type.TEXT, null);
    }

    /**
     * Construit une colonne à partir de la déclaration
     *
     * @return La colonne
     */
    public TreeTableColumn< DTO, T > build() {
        TreeTableColumn< DTO, T > column = new TreeTableColumn<>(Translation.get(title));
        Storage.languageProperty()
               .addListener(( observable, oldValue, newValue ) -> column.setText(Translation.get(title)));
        column.setCellValueFactory(new TreeItemPropertyValueFactory<>(property));
        if ( editable ) {
            column.setCellFactory(getCellFactory());
        }
        column.setOnEditCommit(this::handleSave);
        return column;
    }

    /**
     * Construit la {@code CellFactory} de la colonne
     *
     * @return La {@code CellFactory}
     */
    private Callback< TreeTableColumn< DTO, T >, TreeTableCell< DTO, T > > getCellFactory() {
        return switch ( type ) {
            case TEXT -> TextFieldTreeTableCell.forTreeTableColumn(converter);
            case COMBOBOX -> {
                ObservableList< T > items = FXCollections.observableArrayList(options);
                yield ComboBoxTreeTableCell.forTreeTableColumn(converter, items);
            }
        };
    }

    /**
     * Gère la sauvegarde d'une modification
     *
     * @param event L'événement de modification
     */
    private void handleSave( TreeTableColumn.CellEditEvent< DTO, T > event ) {
        try {
            if ( Objects.isNull(onEditCommit) ) {
                event.getTreeTableView()
                     .getTreeItem(event.getTreeTablePosition().getRow())
                     .getValue()
                     .set(property, event.getNewValue());
                return;
            }
            onEditCommit.handle(event);
        } catch ( ControllerException e ) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Types de colonnes
     */
    public enum Type {
        TEXT,
        COMBOBOX
    }

}

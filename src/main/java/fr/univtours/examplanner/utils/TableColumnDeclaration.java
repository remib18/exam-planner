package fr.univtours.examplanner.utils;

import fr.univtours.examplanner.entities.EditableEntity;
import fr.univtours.examplanner.exceptions.ControllerException;
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

public record TableColumnDeclaration< DTO extends EditableEntity, T >(String property, String title, boolean editable,
                                                                      EventHandler< TreeTableColumn.CellEditEvent< DTO, T > > onEditCommit,
                                                                      StringConverter< T > converter, Type type,
                                                                      List< T > options) {

    public TableColumnDeclaration( String property, String title, boolean editable ) {
        this(property, title, editable, null, (StringConverter< T >) new DefaultStringConverter(), Type.TEXT, null);
    }

    public TreeTableColumn< DTO, T > build() {
        TreeTableColumn< DTO, T > column = new TreeTableColumn<>(title);
        column.setCellValueFactory(new TreeItemPropertyValueFactory<>(property));
        if ( editable ) {
            column.setCellFactory(getCellFactory());
        }
        column.setOnEditCommit(this::handleSave);
        return column;
    }

    private Callback< TreeTableColumn< DTO, T >, TreeTableCell< DTO, T > > getCellFactory() {
        return switch ( type ) {
            case TEXT -> TextFieldTreeTableCell.forTreeTableColumn(converter);
            case COMBOBOX -> {
                ObservableList< T > items = FXCollections.observableArrayList(options);
                yield ComboBoxTreeTableCell.forTreeTableColumn(converter, items);
            }
        };
    }

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

    public enum Type {
        TEXT,
        COMBOBOX
    }

}

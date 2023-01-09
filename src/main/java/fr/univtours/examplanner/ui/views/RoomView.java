package fr.univtours.examplanner.ui.views;

import fr.univtours.examplanner.controllers.RoomController;
import fr.univtours.examplanner.entities.dtos.RoomDTO;
import fr.univtours.examplanner.enums.RoomType;
import fr.univtours.examplanner.exceptions.ControllerException;
import fr.univtours.examplanner.ui.components.DataTable;
import fr.univtours.examplanner.ui.components.DataView;
import fr.univtours.examplanner.utils.TableColumnDeclaration;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.converter.IntegerStringConverter;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RoomView {

    public static final String TITLE = "app.title.room";

    private RoomView() {super();}

    public static @NotNull Scene getScene() throws IOException {
        DataView< RoomDTO > view = new DataView<>("images/Room.png",
                "feature.room",
                new DataTable<>(getColumns(), RoomView::getData)
        );
        view.setOnAddRequest(() -> new RoomDTO("<name>",
                0,
                RoomType.Amphitheater,
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
        ));
        view.setOnSaveRequest(room -> {
            try {
                RoomController.save(room);
            } catch ( ControllerException e ) {
                throw new RuntimeException(e);
            }
        });
        view.setOnDeleteRequest(() -> {
            view.getTable().getSelectionModel().getSelectedItems().forEach(item -> {
                try {RoomController.delete(item.getValue());} catch ( ControllerException e ) {
                    throw new RuntimeException(e);
                }
            });
        });
        return new Scene(view);
    }

    private static @NotNull List< TableColumnDeclaration< RoomDTO, ? > > getColumns() {
        List< TableColumnDeclaration< RoomDTO, ? > > columns = new ArrayList<>();
        columns.add(new TableColumnDeclaration<>("name", "exam.name", true));
        columns.add(new TableColumnDeclaration<>("places",
                "exam.places",
                true,
                RoomView::handlePlacesChange,
                new IntegerStringConverter(),
                TableColumnDeclaration.Type.TEXT,
                null
        ));
        columns.add(new TableColumnDeclaration<>("type",
                "exam.type",
                true,
                RoomView::handleTypeChange,
                RoomType.getConverter(),
                TableColumnDeclaration.Type.COMBOBOX,
                List.of(RoomType.values())
        ));
        return columns;
    }

    private static @NotNull TreeItem< RoomDTO > getData() {
        TreeItem< RoomDTO > root = new TreeItem<>();
        root.setExpanded(true);

        try {
            List< RoomDTO > rooms = RoomController.getAll();
            for ( RoomDTO room : rooms ) {
                TreeItem< RoomDTO > item = new TreeItem<>(room);
                root.getChildren().add(item);
            }
        } catch ( ControllerException e ) {
            throw new RuntimeException(e);
        }
        return root;
    }

    private static void handlePlacesChange( TreeTableColumn.CellEditEvent< RoomDTO, Integer > event ) {
        RoomDTO room = event.getRowValue().getValue();
        Integer newValue = event.getNewValue();

        room.setPlaces(newValue);
        try {
            RoomController.save(room);
        } catch ( ControllerException e ) {
            throw new RuntimeException(e);
        }
    }

    private static void handleTypeChange( TreeTableColumn.CellEditEvent< RoomDTO, RoomType > event ) {
        RoomDTO room = event.getRowValue().getValue();
        RoomType newValue = event.getNewValue();

        room.setType(newValue);
        try {
            RoomController.save(room);
        } catch ( ControllerException e ) {
            throw new RuntimeException(e);
        }

    }

}
package fr.univtours.examplanner.ui.views;

import fr.univtours.examplanner.controllers.RoomController;
import fr.univtours.examplanner.entities.dtos.RoomDTO;
import fr.univtours.examplanner.exceptions.ControllerException;
import fr.univtours.examplanner.ui.components.DataTable;
import fr.univtours.examplanner.ui.components.DataView;
import fr.univtours.examplanner.utils.TableColumnDeclaration;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RoomView {

    public static final String TITLE = "app.title.room";

    private RoomView() {super();}

    public static @NotNull Scene getScene() throws IOException {
        DataView< RoomDTO > view = new DataView<>("images/Group.png",
                "feature.group",
                new DataTable<>(getColumns(), RoomView::getData)
        );
        view.setOnAddRequest(() -> new RoomDTO("<name>",
                0,
                "",
                new ArrayList<>(),
                new ArrayList<>(),
                new ArrayList<>()
        ));
        view.setOnSaveRequest(group -> {
            try {
                RoomController.save(group);
            } catch ( ControllerException e ) {
                e.printStackTrace();
            }
        });
        view.setOnDeleteRequest(() -> {
            view.getTable().getSelectionModel().getSelectedItems().forEach(item -> {
                try {RoomController.delete(item.getValue());} catch ( ControllerException e ) {e.printStackTrace();}
            });
        });
        return new Scene(view);
    }

    private static @NotNull List< TableColumnDeclaration< RoomDTO, ? > > getColumns() {
        List< TableColumnDeclaration< RoomDTO, ? > > columns = new ArrayList<>();
        columns.add(new TableColumnDeclaration<>("name", "exam.name", true));
        // TODO: complete
        return columns;
    }


    private static @NotNull TreeItem< RoomDTO > getData() {
        TreeItem< RoomDTO > root = new TreeItem<>();
        root.setExpanded(true);

        try {
            List< RoomDTO > groups = RoomController.getAll();
            for ( RoomDTO group : groups ) {
                TreeItem< RoomDTO > item = new TreeItem<>(group);
                root.getChildren().add(item);
            }
        } catch ( ControllerException ignored ) {}
        return root;
    }

}
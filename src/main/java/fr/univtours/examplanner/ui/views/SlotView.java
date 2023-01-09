package fr.univtours.examplanner.ui.views;

import fr.univtours.examplanner.controllers.SlotController;
import fr.univtours.examplanner.entities.dtos.SlotDTO;
import fr.univtours.examplanner.exceptions.ControllerException;
import fr.univtours.examplanner.ui.components.DataTable;
import fr.univtours.examplanner.ui.components.DataView;
import fr.univtours.examplanner.utils.TableColumnDeclaration;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SlotView {

    public static final String TITLE = "app.title.slot";

    private SlotView() {super();}

    public static @NotNull Scene getScene() throws IOException {
        DataView< SlotDTO > view = new DataView<>("images/Group.png",
                "feature.group",
                new DataTable<>(getColumns(), SlotView::getData)
        );
        view.setOnAddRequest(() -> new SlotDTO("<name>", new Calendar.Builder().build(), 1));
        view.setOnSaveRequest(group -> {
            try {
                SlotController.save(group);
            } catch ( ControllerException e ) {
                e.printStackTrace();
            }
        });
        view.setOnDeleteRequest(() -> {
            view.getTable().getSelectionModel().getSelectedItems().forEach(item -> {
                try {SlotController.delete(item.getValue());} catch ( ControllerException e ) {e.printStackTrace();}
            });
        });
        return new Scene(view);
    }

    private static @NotNull List< TableColumnDeclaration< SlotDTO, ? > > getColumns() {
        List< TableColumnDeclaration< SlotDTO, ? > > columns = new ArrayList<>();
        columns.add(new TableColumnDeclaration<>("name", "department.name", true));
        return columns;
    }


    private static @NotNull TreeItem< SlotDTO > getData() {
        TreeItem< SlotDTO > root = new TreeItem<>();
        root.setExpanded(true);

        try {
            List< SlotDTO > groups = SlotController.getAll();
            for ( SlotDTO group : groups ) {
                TreeItem< SlotDTO > item = new TreeItem<>(group);
                root.getChildren().add(item);
            }
        } catch ( ControllerException ignored ) {}
        return root;
    }

}
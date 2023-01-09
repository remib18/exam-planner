package fr.univtours.examplanner.ui.views;

import fr.univtours.examplanner.controllers.SlotController;
import fr.univtours.examplanner.entities.dtos.SlotDTO;
import fr.univtours.examplanner.exceptions.ControllerException;
import fr.univtours.examplanner.ui.components.DataTable;
import fr.univtours.examplanner.ui.components.DataView;
import fr.univtours.examplanner.utils.TableColumnDeclaration;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.FloatStringConverter;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class SlotView {

    public static final String TITLE = "app.title.slot";

    private SlotView() {super();}

    public static @NotNull Scene getScene() throws IOException {
        DataView< SlotDTO > view = new DataView<>("images/Slot.png",
                "feature.slot",
                new DataTable<>(getColumns(), SlotView::getData)
        );
        view.setOnAddRequest(() -> new SlotDTO(null, new Date(2023, 01, 01), 3, 1));
        view.setOnSaveRequest(slot -> {
            try {
                SlotController.save(slot);
            } catch ( ControllerException e ) {
                throw new RuntimeException(e);
            }
        });
        view.setOnDeleteRequest(() -> {
            view.getTable().getSelectionModel().getSelectedItems().forEach(item -> {
                try {SlotController.delete(item.getValue());} catch ( ControllerException e ) {
                    throw new RuntimeException(e);
                }
            });
        });
        return new Scene(view);
    }

    private static @NotNull List< TableColumnDeclaration< SlotDTO, ? > > getColumns() {
        List< TableColumnDeclaration< SlotDTO, ? > > columns = new ArrayList<>();
        columns.add(new TableColumnDeclaration<>("id", "slot.id", false));
        columns.add(new TableColumnDeclaration<>("day", "slot.day", false, new DateStringConverter()));
        columns.add(new TableColumnDeclaration<>("hour", "slot.hour", true, new FloatStringConverter()));
        columns.add(new TableColumnDeclaration<>("duration", "slot.duration", true, new FloatStringConverter()));
        return columns;
    }


    private static @NotNull TreeItem< SlotDTO > getData() {
        TreeItem< SlotDTO > root = new TreeItem<>();
        root.setExpanded(true);

        try {
            List< SlotDTO > slots = SlotController.getAll();
            for ( SlotDTO slot : slots ) {
                TreeItem< SlotDTO > item = new TreeItem<>(slot);
                root.getChildren().add(item);
            }
        } catch ( ControllerException e ) {
            throw new RuntimeException(e);
        }
        return root;
    }

}
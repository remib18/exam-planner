package fr.univtours.examplanner.ui.views;

import fr.univtours.examplanner.controllers.SubjectController;
import fr.univtours.examplanner.entities.dtos.SubjectDTO;
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

public class SubjectView {

    public static final String TITLE = "app.title.slot";

    private SubjectView() {super();}

    public static @NotNull Scene getScene() throws IOException {
        DataView< SubjectDTO > view = new DataView<>("images/Group.png",
                "feature.group",
                new DataTable<>(getColumns(), SubjectView::getData)
        );
        view.setOnAddRequest(() -> new SubjectDTO(null, "<name>"));
        view.setOnSaveRequest(group -> {
            try {
                SubjectController.save(group);
            } catch ( ControllerException e ) {
                e.printStackTrace();
            }
        });
        view.setOnDeleteRequest(() -> {
            view.getTable().getSelectionModel().getSelectedItems().forEach(item -> {
                try {SubjectController.delete(item.getValue());} catch ( ControllerException e ) {e.printStackTrace();}
            });
        });
        return new Scene(view);
    }

    private static @NotNull List< TableColumnDeclaration< SubjectDTO, ? > > getColumns() {
        List< TableColumnDeclaration< SubjectDTO, ? > > columns = new ArrayList<>();
        columns.add(new TableColumnDeclaration<>("id", "mockup.id", false));
        columns.add(new TableColumnDeclaration<>("name", "mockup.name", true));
        return columns;
    }


    private static @NotNull TreeItem< SubjectDTO > getData() {
        TreeItem< SubjectDTO > root = new TreeItem<>();
        root.setExpanded(true);

        try {
            List< SubjectDTO > groups = SubjectController.getAll();
            for ( SubjectDTO group : groups ) {
                TreeItem< SubjectDTO > item = new TreeItem<>(group);
                root.getChildren().add(item);
            }
        } catch ( ControllerException ignored ) {}
        return root;
    }

}
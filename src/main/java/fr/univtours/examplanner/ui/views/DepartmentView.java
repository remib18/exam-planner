package fr.univtours.examplanner.ui.views;

import fr.univtours.examplanner.controllers.DepartmentController;
import fr.univtours.examplanner.entities.dtos.DepartmentDTO;
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

public class DepartmentView {

    public static final String TITLE = "app.title.department";

    private DepartmentView() {super();}

    public static @NotNull Scene getScene() throws IOException {
        DataView< DepartmentDTO > view = new DataView<>("images/Group.png",
                "feature.group",
                new DataTable<>(getColumns(), DepartmentView::getData)
        );
        view.setOnAddRequest(() -> new DepartmentDTO("<name>"));
        view.setOnSaveRequest(group -> {
            try {
                DepartmentController.save(group);
            } catch ( ControllerException e ) {
                e.printStackTrace();
            }
        });
        view.setOnDeleteRequest(() -> {
            view.getTable().getSelectionModel().getSelectedItems().forEach(item -> {
                try {DepartmentController.delete(item.getValue());} catch ( ControllerException e ) {
                    e.printStackTrace();
                }
            });
        });
        return new Scene(view);
    }

    private static @NotNull List< TableColumnDeclaration< DepartmentDTO, ? > > getColumns() {
        List< TableColumnDeclaration< DepartmentDTO, ? > > columns = new ArrayList<>();
        columns.add(new TableColumnDeclaration<>("name", "department.name", true));
        return columns;
    }


    private static @NotNull TreeItem< DepartmentDTO > getData() {
        TreeItem< DepartmentDTO > root = new TreeItem<>();
        root.setExpanded(true);

        List< DepartmentDTO > groups = DepartmentController.getAll();
        for ( DepartmentDTO group : groups ) {
            TreeItem< DepartmentDTO > item = new TreeItem<>(group);
            root.getChildren().add(item);
        }
        return root;
    }

}
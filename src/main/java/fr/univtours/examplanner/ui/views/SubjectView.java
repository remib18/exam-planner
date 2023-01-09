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

    public static final String TITLE = "app.title.subject";

    private SubjectView() {super();}

    public static @NotNull Scene getScene() throws IOException {
        DataView< SubjectDTO > view = new DataView<>("images/Managers.png",
                "feature.subject",
                new DataTable<>(getColumns(), SubjectView::getData)
        );
        view.setOnAddRequest(() -> new SubjectDTO(null, "<name>"));
        view.setOnSaveRequest(subject -> {
            try {
                SubjectController.save(subject);
            } catch ( ControllerException e ) {
                throw new RuntimeException(e);
            }
        });
        view.setOnDeleteRequest(() -> {
            view.getTable().getSelectionModel().getSelectedItems().forEach(item -> {
                try {SubjectController.delete(item.getValue());} catch ( ControllerException e ) {
                    throw new RuntimeException(e);
                }
            });
        });
        return new Scene(view);
    }

    private static @NotNull List< TableColumnDeclaration< SubjectDTO, ? > > getColumns() {
        List< TableColumnDeclaration< SubjectDTO, ? > > columns = new ArrayList<>();
        columns.add(new TableColumnDeclaration<>("name", "subject.name", false));
        return columns;
    }


    private static @NotNull TreeItem< SubjectDTO > getData() {
        TreeItem< SubjectDTO > root = new TreeItem<>();
        root.setExpanded(true);

        try {
            List< SubjectDTO > subjects = SubjectController.getAll();
            for ( SubjectDTO subject : subjects ) {
                TreeItem< SubjectDTO > item = new TreeItem<>(subject);
                root.getChildren().add(item);
            }
        } catch ( ControllerException e ) {
            throw new RuntimeException(e);
        }
        return root;
    }

}
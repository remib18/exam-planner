package fr.univtours.examplanner.ui.views;

import fr.univtours.examplanner.controllers.ExamController;
import fr.univtours.examplanner.entities.dtos.ExamDTO;
import fr.univtours.examplanner.enums.ExamType;
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

public class ExamView {

    public static final String TITLE = "app.title.exam";

    private ExamView() {super();}

    public static @NotNull Scene getScene() throws IOException {
        DataView< ExamDTO > view = new DataView<>("images/Group.png",
                "feature.group",
                new DataTable<>(getColumns(), ExamView::getData)
        );
        view.setOnAddRequest(() -> new ExamDTO(null,
                "<name>",
                ExamType.Final,
                new ArrayList<>(),
                new ArrayList<>(),
                "",
                2,
                new ArrayList<>()
        ));
        view.setOnSaveRequest(group -> {
            try {
                ExamController.save(group);
            } catch ( ControllerException e ) {
                e.printStackTrace();
            }
        });
        view.setOnDeleteRequest(() -> {
            view.getTable().getSelectionModel().getSelectedItems().forEach(item -> {
                try {ExamController.delete(item.getValue());} catch ( ControllerException e ) {e.printStackTrace();}
            });
        });
        return new Scene(view);
    }

    private static @NotNull List< TableColumnDeclaration< ExamDTO, ? > > getColumns() {
        List< TableColumnDeclaration< ExamDTO, ? > > columns = new ArrayList<>();
        columns.add(new TableColumnDeclaration<>("id", "exam.id", false));
        columns.add(new TableColumnDeclaration<>("name", "exam.name", true));
        columns.add(new TableColumnDeclaration<>("type",
                "exam.type",
                true,
                null,
                ExamType.getConverter(),
                TableColumnDeclaration.Type.COMBOBOX,
                List.of(ExamType.values())
        ));
        return columns;
    }


    private static @NotNull TreeItem< ExamDTO > getData() {
        TreeItem< ExamDTO > root = new TreeItem<>();
        root.setExpanded(true);

        try {
            List< ExamDTO > groups = ExamController.getAll();
            for ( ExamDTO group : groups ) {
                TreeItem< ExamDTO > item = new TreeItem<>(group);
                root.getChildren().add(item);
            }
        } catch ( ControllerException ignored ) {}
        return root;
    }

}
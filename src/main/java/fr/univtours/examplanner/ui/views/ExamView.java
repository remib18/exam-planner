package fr.univtours.examplanner.ui.views;

import fr.univtours.examplanner.controllers.ExamController;
import fr.univtours.examplanner.controllers.SubjectController;
import fr.univtours.examplanner.entities.dtos.ExamDTO;
import fr.univtours.examplanner.entities.dtos.SubjectDTO;
import fr.univtours.examplanner.enums.ExamType;
import fr.univtours.examplanner.exceptions.ControllerException;
import fr.univtours.examplanner.ui.components.DataTable;
import fr.univtours.examplanner.ui.components.DataView;
import fr.univtours.examplanner.utils.TableColumnDeclaration;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.StringConverter;
import javafx.util.converter.FloatStringConverter;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExamView {

    public static final String TITLE = "app.title.exam";

    private static ExamView instance;

    private final ExamController controller;

    private ExamView() {
        super();
        controller = new ExamController();
    }

    public static @NotNull Scene getScene() throws IOException {
        DataView< ExamDTO > view = new DataView<>("images/Examen.png",
                "feature.exam",
                new DataTable<>(getColumns(), ExamView::getData)
        );
        view.setOnAddRequest(() -> new ExamDTO(null,
                "<name>",
                ExamType.Final,
                new ArrayList<>(),
                new ArrayList<>(),
                "",
                1f,
                new ArrayList<>()
        ));
        view.setOnSaveRequest(exam -> {
            try {
                getInstance().controller.save(exam);
            } catch ( ControllerException e ) {
                e.printStackTrace();
            }
        });
        view.setOnDeleteRequest(() -> {
            view.getTable().getSelectionModel().getSelectedItems().forEach(item -> {
                try {getInstance().controller.delete(item.getValue());} catch ( ControllerException e ) {
                    e.printStackTrace();
                }
            });
        });
        return new Scene(view);
    }

    private static @NotNull List< TableColumnDeclaration< ExamDTO, ? > > getColumns() {
        List< SubjectDTO > subjects = new ArrayList<>();
        try {
            subjects.addAll(SubjectController.getAll());
        } catch ( ControllerException e ) {
            throw new RuntimeException(e);
        }

        List< TableColumnDeclaration< ExamDTO, ? > > columns = new ArrayList<>();
        columns.add(new TableColumnDeclaration<>("id", "feature.exam.id", false));
        columns.add(new TableColumnDeclaration<>("name", "feature.exam.name", true));
        columns.add(new TableColumnDeclaration<>("duration",
                "feature.exam.duration",
                true,
                new FloatStringConverter()
        ));
        columns.add(new TableColumnDeclaration<>("type",
                "feature.exam.type",
                true,
                ExamView::handleTypeChange,
                ExamType.getConverter(),
                TableColumnDeclaration.Type.COMBOBOX,
                List.of(ExamType.values())
        ));
        columns.add(new TableColumnDeclaration<>("subject",
                "feature.exam.subject",
                true,
                ExamView::handleSubjectChange,
                new SubjectStringConverter(),
                TableColumnDeclaration.Type.COMBOBOX,
                subjects
        ));
        return columns;
    }

    private static @NotNull TreeItem< ExamDTO > getData() throws ControllerException {
        TreeItem< ExamDTO > root = new TreeItem<>();
        root.setExpanded(true);
        List< ExamDTO > exams = getInstance().controller.getAll();
        for ( ExamDTO exam : exams ) {
            TreeItem< ExamDTO > item = new TreeItem<>(exam);
            root.getChildren().add(item);
        }
        return root;
    }

    private static ExamView getInstance() {
        if ( Objects.isNull(instance) ) {
            instance = new ExamView();
        }
        return instance;
    }

    private static void handleTypeChange( TreeTableColumn.CellEditEvent< ExamDTO, ExamType > event ) {
        ExamDTO exam = event.getRowValue().getValue();
        ExamType type = event.getNewValue();
        try {
            exam.set("type", type);
        } catch ( ControllerException ignored ) {}
    }

    private static void handleSubjectChange( TreeTableColumn.CellEditEvent< ExamDTO, SubjectDTO > event ) {
        ExamDTO exam = event.getRowValue().getValue();
        SubjectDTO newValue = event.getNewValue();
        exam.setSubject(newValue);
        try {
            ExamController.save(exam);
        } catch ( ControllerException e ) {
            throw new RuntimeException(e);
        }
    }

    private static class SubjectStringConverter extends StringConverter< SubjectDTO > {

        @Override
        public String toString( SubjectDTO object ) {
            return object.getName();
        }

        @Override
        public SubjectDTO fromString( String str ) {
            try {
                return SubjectController.getByName(str);
            } catch ( ControllerException e ) {
                return null;
            }
        }
    }
}
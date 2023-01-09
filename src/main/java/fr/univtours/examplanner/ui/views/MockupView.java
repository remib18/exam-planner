package fr.univtours.examplanner.ui.views;

import fr.univtours.examplanner.controllers.MockUpController;
import fr.univtours.examplanner.entities.dtos.MockUpDTO;
import fr.univtours.examplanner.enums.Degree;
import fr.univtours.examplanner.enums.MockupYear;
import fr.univtours.examplanner.exceptions.ControllerException;
import fr.univtours.examplanner.ui.components.DataTable;
import fr.univtours.examplanner.ui.components.DataView;
import fr.univtours.examplanner.utils.TableColumnDeclaration;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class MockupView {

    public static final String TITLE = "app.title.mockup";

    private MockupView() {super();}

    public static @NotNull Scene getScene() throws IOException {
        DataView< MockUpDTO > view = new DataView<>("images/Mockup.png",
                "feature.mockup",
                new DataTable<>(getColumns(), MockupView::getData)
        );
        view.setOnAddRequest(() -> new MockUpDTO(null,
                "<name>",
                MockupYear.One,
                Degree.Bachelor,
                1,
                new ArrayList<>()
        ));
        view.setOnSaveRequest(group -> {
            try {
                MockUpController.save(group);
            } catch ( ControllerException e ) {
                throw new RuntimeException(e);
            }
        });
        view.setOnDeleteRequest(() -> {
            view.getTable().getSelectionModel().getSelectedItems().forEach(item -> {
                try {MockUpController.delete(item.getValue());} catch ( ControllerException e ) {
                    throw new RuntimeException(e);
                }
            });
        });
        return new Scene(view);
    }

    private static @NotNull List< TableColumnDeclaration< MockUpDTO, ? > > getColumns() {
        List< TableColumnDeclaration< MockUpDTO, ? > > columns = new ArrayList<>();
        columns.add(new TableColumnDeclaration<>("name", "mockup.name", false));
        columns.add(new TableColumnDeclaration<>("degree", "mockup.degree", true));
        columns.add(new TableColumnDeclaration<>("year", "mockup.year", true));
        columns.add(new TableColumnDeclaration<>("semester", "mockup.semester", true));
        columns.add(new TableColumnDeclaration<>("departement", "mockup.department", true));


        return columns;
    }

    private static @NotNull TreeItem< MockUpDTO > getData() {
        TreeItem< MockUpDTO > root = new TreeItem<>();
        root.setExpanded(true);

        try {
            List< MockUpDTO > groups = MockUpController.getAll();
            for ( MockUpDTO group : groups ) {
                TreeItem< MockUpDTO > item = new TreeItem<>(group);
                root.getChildren().add(item);
            }
        } catch ( ControllerException e ) {
            throw new RuntimeException(e);
        }
        return root;
    }

    private static void handleDegreeChange( TreeTableColumn.CellEditEvent< MockUpDTO, Degree > event ) {
        MockUpDTO mockUp = event.getRowValue().getValue();
        Degree degree = event.getNewValue();
        mockUp.setDegree(degree);
        try {
            MockUpController.save(mockUp);
        } catch ( ControllerException e ) {
            throw new RuntimeException(e);
        }
    }

    private static void handleYearChange( TreeTableColumn.CellEditEvent< MockUpDTO, Year > event ) {
        MockUpDTO mockUp = event.getRowValue().getValue();
        Year year = event.getNewValue();
        //mockUp.setYear(year);
        try {
            MockUpController.save(mockUp);
        } catch ( ControllerException e ) {
            throw new RuntimeException(e);
        }
    }

    private static void handleSemesterChange( TreeTableColumn.CellEditEvent< MockUpDTO, Integer > event ) {
        MockUpDTO mockUp = event.getRowValue().getValue();
        Integer newValue = event.getNewValue();
        switch ( newValue ) {
            case 1, 2 -> mockUp.setSemester(newValue);
            default -> throw new RuntimeException();
        }
        //mockUp.setYear(year);
        try {
            MockUpController.save(mockUp);
        } catch ( ControllerException e ) {
            throw new RuntimeException(e);
        }
    }

}
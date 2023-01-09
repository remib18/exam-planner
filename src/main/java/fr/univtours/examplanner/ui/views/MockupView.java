package fr.univtours.examplanner.ui.views;

import fr.univtours.examplanner.controllers.MockUpController;
import fr.univtours.examplanner.entities.dtos.MockUpDTO;
import fr.univtours.examplanner.enums.Degree;
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

public class MockupView {

    public static final String TITLE = "app.title.slot";

    private MockupView() {super();}

    public static @NotNull Scene getScene() throws IOException {
        DataView< MockUpDTO > view = new DataView<>("images/Group.png",
                "feature.group",
                new DataTable<>(getColumns(), MockupView::getData)
        );
        view.setOnAddRequest(() -> new MockUpDTO(null, "<name>", Degree.Bachelor, 1, new ArrayList<>()));
        view.setOnSaveRequest(group -> {
            try {
                MockUpController.save(group);
            } catch ( ControllerException e ) {
                e.printStackTrace();
            }
        });
        view.setOnDeleteRequest(() -> {
            view.getTable().getSelectionModel().getSelectedItems().forEach(item -> {
                try {MockUpController.delete(item.getValue());} catch ( ControllerException e ) {e.printStackTrace();}
            });
        });
        return new Scene(view);
    }

    private static @NotNull List< TableColumnDeclaration< MockUpDTO, ? > > getColumns() {
        List< TableColumnDeclaration< MockUpDTO, ? > > columns = new ArrayList<>();
        columns.add(new TableColumnDeclaration<>("id", "mockup.id", false));
        columns.add(new TableColumnDeclaration<>("name", "mockup.name", true));
        // TODO: complete
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
        } catch ( ControllerException ignored ) {}
        return root;
    }

}
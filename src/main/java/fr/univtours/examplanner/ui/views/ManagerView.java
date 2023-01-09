package fr.univtours.examplanner.ui.views;

import fr.univtours.examplanner.controllers.ManagerController;
import fr.univtours.examplanner.entities.dtos.ManagerDTO;
import fr.univtours.examplanner.enums.Civility;
import fr.univtours.examplanner.exceptions.ControllerException;
import fr.univtours.examplanner.ui.components.DataTable;
import fr.univtours.examplanner.ui.components.DataView;
import fr.univtours.examplanner.utils.TableColumnDeclaration;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ManagerView {

    public static final String TITLE = "app.title.manager";

    private static ManagerView instance;

    private final ManagerController controller;

    public ManagerView(){
        super();
        controller = new ManagerController();
    }

    private static ManagerView getInstance() {
        if ( Objects.isNull(instance) ) {
            instance = new ManagerView();
        }
        return instance;
    }

    public static @NotNull Scene getScene() throws IOException {
        DataView< ManagerDTO > view = new DataView<>("images/Person.png",
                "feature.manager",
                new DataTable<>(getColumns(), ManagerView::getData)

        );
        view.setOnAddRequest(() -> new ManagerDTO(null, Civility.Men, "<lastanme>", "<firstname>"));
        view.setOnSaveRequest(manager -> {
            try {
                getInstance().controller.save(manager);
            } catch ( ControllerException e ) {
                e.printStackTrace();
            }
        });
        view.setOnDeleteRequest(() -> {
            view.getTable().getSelectionModel().getSelectedItems().forEach(item -> {
                try {getInstance().controller.delete(item.getValue());} catch ( ControllerException e ) {e.printStackTrace();}
            });
        });
        return new Scene(view);
    }

    private static @NotNull List< TableColumnDeclaration< ManagerDTO, ? > > getColumns() {
        List< TableColumnDeclaration< ManagerDTO, ? > > columns = new ArrayList<>();
        columns.add(new TableColumnDeclaration<>("id", "feature.manager.id", false));
        columns.add(new TableColumnDeclaration<>("civility",
                "feature.manager.civility",
                true,
                ManagerView::handleCivilityChange,
                Civility.getConverter(),
                TableColumnDeclaration.Type.COMBOBOX,
                Civility.getOptions()
        ));
        columns.add(new TableColumnDeclaration<>("lastname", "feature.manager.lastname", true));
        columns.add(new TableColumnDeclaration<>("firstname", "feature.manager.firstname", true));
        return columns;
    }

    private static @NotNull TreeItem< ManagerDTO > getData() throws ControllerException {
        TreeItem< ManagerDTO > root = new TreeItem<>();
        root.setExpanded(true);
        List< ManagerDTO > managers = getInstance().controller.getAll();
        for ( ManagerDTO manager : managers ) {
            TreeItem< ManagerDTO > item = new TreeItem<>(manager);
            root.getChildren().add(item);
        }
        return root;
    }

    private static void handleCivilityChange( TreeTableColumn.CellEditEvent< ManagerDTO, Civility > event ) {
        ManagerDTO manager = event.getRowValue().getValue();
        Civility civility = event.getNewValue();
        try {
            manager.set("civility", civility);
        } catch ( ControllerException ignored ) {}
    }

}
package fr.univtours.examplanner.ui.views;

import fr.univtours.examplanner.controllers.GroupController;
import fr.univtours.examplanner.entities.dtos.GroupDTO;
import fr.univtours.examplanner.exceptions.ControllerException;
import fr.univtours.examplanner.ui.components.DataTable;
import fr.univtours.examplanner.ui.components.DataView;
import fr.univtours.examplanner.utils.TableColumnDeclaration;
import fr.univtours.examplanner.utils.Tree;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GroupView {

	public static final String TITLE = "app.title.group";

	private static GroupView instance;

	private final GroupController controller;

	private static GroupView getInstance() {
		if ( Objects.isNull(instance) ) {
			instance = new GroupView();
		}
		return instance;
	}

	private GroupView() {
		super();
		controller = new GroupController();
	}

	public static @NotNull Scene getScene() throws IOException {
		DataView< GroupDTO > view = new DataView<>("images/Group.png",
				"feature.group",
				new DataTable<>(getColumns(), GroupView::getData)
		);
		view.setOnAddRequest(() -> new GroupDTO(null, "<name>", true, 0, 0, 0, 0, new ArrayList<>()));
		view.setOnSaveRequest(group -> {
			try {
				getInstance().controller.save(group);
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

	private static @NotNull List< TableColumnDeclaration< GroupDTO, ? > > getColumns() {
		List< TableColumnDeclaration< GroupDTO, ? > > columns = new ArrayList<>();
		columns.add(new TableColumnDeclaration<>("id", "feature.group.id", false));
		columns.add(new TableColumnDeclaration<>("name", "feature.manager.name", true));
		columns.add(new TableColumnDeclaration<>("containsStudentsWithReducedMobility",
				"feature.group.containsStudentsWithReducedMobility",
				true
		));
		columns.add(new TableColumnDeclaration<>("numberOfStudentsWithWriterNeeds",
				"feature.group.numberOfStudentsWithWriterNeeds",
				true
		));
		columns.add(new TableColumnDeclaration<>("numberOfStudentsWithIsolationNeeds",
				"feature.group.numberOfStudentsWithIsolationNeeds",
				true
		));
		columns.add(new TableColumnDeclaration<>("numberOfStudentsWithPartTimeNeeds",
				"feature.group.numberOfStudentsWithPartTimeNeeds",
				true
		));
		columns.add(new TableColumnDeclaration<>("numberOfStudentsWithoutAdjustment",
				"feature.group.numberOfStudentsWithoutAdjustment",
				true
		));
		columns.add(new TableColumnDeclaration<>("childrenIDs", "feature.group.childrenIDs", false));
		return columns;
	}


	private static @NotNull TreeItem< GroupDTO > getData() {
		TreeItem< GroupDTO > root = new TreeItem<>();
		root.setExpanded(true);

		List< GroupDTO > groups = null;
		try {
			groups = getInstance().controller.getAll();
			for ( GroupDTO group : groups ) {
				TreeItem< GroupDTO > item = new TreeItem<>(group);
				item.getChildren().addAll(Tree.fromList(group.getChildren()));
				root.getChildren().add(item);
			}
		} catch ( ControllerException ignored ) {}
		return root;
	}
}
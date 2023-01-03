package fr.univtours.examplanner.ui.views;

import fr.univtours.examplanner.controllers.UserController;
import fr.univtours.examplanner.entities.dtos.UserDTO;
import fr.univtours.examplanner.enums.UserRole;
import fr.univtours.examplanner.exceptions.ControllerException;
import fr.univtours.examplanner.ui.PopupController;
import fr.univtours.examplanner.ui.components.DataTable;
import fr.univtours.examplanner.ui.components.DataView;
import fr.univtours.examplanner.ui.controllers.popups.EditUserRolePopupController;
import fr.univtours.examplanner.utils.TableColumnDeclaration;
import fr.univtours.examplanner.utils.Tree;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserView {

	public static final String TITLE = "app.title.user";

	private static final SimpleObjectProperty< Boolean > isEditing = new SimpleObjectProperty<>(false);

	private static final SimpleObjectProperty< UserDTO > editedUser = new SimpleObjectProperty<>(null);

	private UserView() {super();}

	public static @NotNull Scene getScene() throws IOException {
		DataView< UserDTO > view = new DataView<>("images/Person.png",
				"feature.user",
				new DataTable<>(getColumns(), UserView::getData)
		);
		view.setOnAddRequest(() -> {
			// Add an empty entry if there is no entry with a null id
			// else save the entry
			List< UserDTO > data = Tree.treeItemToList(view.getTable().getRoot());
			if ( isEditing.get() ) {
				System.out.println(data);
				try {
					UserController.save(editedUser.get());
				} catch ( ControllerException e ) {
					e.printStackTrace();
				}
				isEditing.set(false);
				view.getTable().refresh();
				return;
			}
			TreeItem< UserDTO > root = view.getTable().getRoot();
			editedUser.set(new UserDTO(null, "<email>", "<mot de passe>", null, null));
			root.getChildren().add(new TreeItem<>(editedUser.get()));
			view.getTable().setRoot(root);
			isEditing.set(true);
		});
		view.setOnDeleteRequest(() -> {
			view.getTable().getSelectionModel().getSelectedItems().forEach(item -> {
				try {
					UserController.delete(item.getValue());
				} catch ( ControllerException e ) {
					e.printStackTrace();
				}
			});
			view.getTable().refresh();
		});
		return new Scene(view);
	}

	private static @NotNull List< TableColumnDeclaration< UserDTO, ? > > getColumns() {
		List< TableColumnDeclaration< UserDTO, ? > > columns = new ArrayList<>();
		columns.add(new TableColumnDeclaration<>("id", "feature.user.id", false));
		columns.add(new TableColumnDeclaration<>("mail", "feature.user.mail", true));
		columns.add(new TableColumnDeclaration<>("role",
				"feature.user.role",
				true,
				UserView::handleRoleChange,
				UserRole.getConverter(),
				TableColumnDeclaration.Type.COMBOBOX,
				UserRole.getOptions()
		));
		columns.add(new TableColumnDeclaration<>("managerID", "feature.user.managerAccount", false));
		columns.add(new TableColumnDeclaration<>("departmentID", "feature.department", false));
		return columns;
	}

	private static @NotNull TreeItem< UserDTO > getData() {
		TreeItem< UserDTO > root = new TreeItem<>();
		root.setExpanded(true);

		try {
			List< UserDTO > users = UserController.getAll();
			for ( UserDTO user : users ) {
				TreeItem< UserDTO > item = new TreeItem<>(user);
				root.getChildren().add(item);
			}
		} catch ( ControllerException ignored ) {}

		return root;
	}

	private static void handleRoleChange( TreeTableColumn.CellEditEvent< UserDTO, UserRole > event ) {
		UserDTO user = event.getRowValue().getValue();
		UserRole role = event.getNewValue();

		( new PopupController("popups.user.edit.role",
				"popups/user-role-change.fxml",
				new EditUserRolePopupController(user, role)
		)
		).open();

	}
}
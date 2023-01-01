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
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserView {

	public static final String TITLE = "app.title.user";

	private UserView() {super();}

	public static @NotNull Scene getScene() throws IOException {
		DataView< UserDTO > view = new DataView<>("images/Person.png",
				"feature.user",
				new DataTable<>(getColumns(), getData())
		);
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

		System.out.println("Role change: " + role);

		( new PopupController("popups.user.edit.role",
				"popups/user-role-change.fxml",
				new EditUserRolePopupController(user, role)
		)
		).open();

	}
}
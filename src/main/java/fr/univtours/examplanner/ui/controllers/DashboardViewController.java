package fr.univtours.examplanner.ui.controllers;

import fr.univtours.examplanner.Storage;
import fr.univtours.examplanner.controllers.AuthenticationController;
import fr.univtours.examplanner.enums.Scenes;
import fr.univtours.examplanner.enums.UserRole;
import fr.univtours.examplanner.translations.Translation;
import fr.univtours.examplanner.ui.BasicViewController;
import fr.univtours.examplanner.ui.PopupController;
import fr.univtours.examplanner.ui.components.DashboardTile;
import fr.univtours.examplanner.ui.controllers.popups.ResetPasswordPopupController;
import javafx.fxml.FXML;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class DashboardViewController extends BasicViewController {

    @FXML
    private Text helloText;

    @FXML
    private Text userName;

    @FXML
    private Text signOutText;

    @FXML
    private Text changePasswordText;

    @FXML
    private FlowPane tiles;

    @FXML
    private void handleDisconnect() {
        AuthenticationController.logout();
    }

    @FXML
    private void handleUpdatePassword() {
        PopupController popup = new PopupController(
                "",
                "popups/reset-password.fxml",
                new ResetPasswordPopupController()
        );
        popup.open();
    }

    @Override
    protected void init() {
        userName.setText(Storage.getUser().getMail());
        loadTiles();
    }

    private void loadTiles() {
        try {
            if ( AuthenticationController.checkAccessRights(UserRole.Department) ) {
				loadTile("feature.exam", "images/Examen.png", Scenes.Exam);
				loadTile("feature.manager", "images/Managers.png", Scenes.Manager);
				loadTile("feature.mockup", "images/Managers.png", Scenes.Mockup);
				loadTile("feature.subject", "images/Managers.png", Scenes.Subject);
			}
			if ( AuthenticationController.checkAccessRights(UserRole.Schooling) ) {
				loadTile("feature.department", "images/Department.png", Scenes.Department);
				loadTile("feature.group", "images/Group.png", Scenes.Group);
				loadTile("feature.room", "images/Room.png", Scenes.Room);
				loadTile("feature.slot", "images/Slot.png", Scenes.Slot);
				loadTile("feature.user", "images/Person.png", Scenes.User);
			}
		} catch ( IOException e ) {
			throw new RuntimeException(e);
		}
	}

	private void loadTile(
			@NotNull String title, @NotNull String image, @NotNull Scenes target
	) throws IOException {
		DashboardTile tile = new DashboardTile(title, image);
		tile.setOnClick(() -> {
			Storage.setScene(target);
			return null;
		});
		tiles.getChildren().add(tile);
	}

	@Override
	protected void onLanguageUpdate() {
		helloText.setText(Translation.get("ui.dashboard.hello"));
        signOutText.setText(Translation.get("ui.dashboard.signout"));
        changePasswordText.setText(Translation.get("ui.dashboard.change-password"));
	}

}

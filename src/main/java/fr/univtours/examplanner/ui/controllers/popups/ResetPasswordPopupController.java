package fr.univtours.examplanner.ui.controllers.popups;

import fr.univtours.examplanner.Storage;
import fr.univtours.examplanner.controllers.AuthenticationController;
import fr.univtours.examplanner.controllers.UserController;
import fr.univtours.examplanner.entities.dtos.UserDTO;
import fr.univtours.examplanner.exceptions.ControllerException;
import fr.univtours.examplanner.translations.Translation;
import fr.univtours.examplanner.ui.BasicViewController;
import fr.univtours.examplanner.ui.PopupController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;

import java.util.Objects;

public class ResetPasswordPopupController extends BasicViewController {

    @FXML
    private Text updateMyPasswordText;

    @FXML
    private Text passwordText;

    @FXML
    private Text confirmText;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Button saveBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private void handleCancel() {
        PopupController.close();
    }

    @FXML
    private void handleSave() throws ControllerException {
        UserDTO user = Objects.requireNonNull(Storage.getUser());
        if ( passwordField.getText().equals(confirmPasswordField.getText()) && !passwordField.getText().isEmpty() ) {

            user.setPassword(AuthenticationController.encryptPassword(passwordField.getText()));
            UserController.save(user);
            PopupController.close();
        }
    }

    @Override
    protected void init() {}

    @Override
    protected void onLanguageUpdate() {
        saveBtn.setText(Translation.get("actions.save"));
        cancelBtn.setText(Translation.get("actions.cancel"));
        updateMyPasswordText.setText(Translation.get("popups.reset-password.update-my-password"));
        passwordText.setText(Translation.get("popups.reset-password.password"));
        confirmText.setText(Translation.get("popups.reset-password.confirm"));
    }

}

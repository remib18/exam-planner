package fr.univtours.examplanner.ui.controllers.popups;

import fr.univtours.examplanner.translations.Translation;
import fr.univtours.examplanner.ui.BasicViewController;
import fr.univtours.examplanner.ui.PopupController;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class InvalidCredentialsPopupController extends BasicViewController {

    @FXML
    private Text messageText;

    @FXML
    private Text titleText;

    @FXML
    private void handleClose() {
        PopupController.close();
    }

    @Override
    protected void init() {}

    @Override
    protected void onLanguageUpdate() {
        titleText.setText(Translation.get("popups.invalid-credentials.title"));
        messageText.setText(Translation.get("popups.invalid-credentials.message"));
    }
}

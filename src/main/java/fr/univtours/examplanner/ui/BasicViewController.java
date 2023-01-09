package fr.univtours.examplanner.ui;

import fr.univtours.examplanner.Storage;
import fr.univtours.examplanner.exceptions.ControllerException;
import javafx.fxml.FXML;

public abstract class BasicViewController {

	/**
	 * Initialise la vue<br/>
	 * Ne pas {@code Override}, utiliser {@link #init()} à la place
	 */
	@FXML
	private void initialize() throws ControllerException {

		Storage.languageProperty().addListener(( observable, oldValue, newValue ) -> {
			onLanguageUpdate();
		});
		onLanguageUpdate();

		init();
	}

	/**
	 * Poursuit l'initialisation de la vue
	 */
	protected abstract void init() throws ControllerException;

	/**
	 * Appelé lorsque la langue est changée et au chargement de la vue
	 *
	 * @implNote Mettre ici tout le code relatif au chargement des traductions
	 */
	protected abstract void onLanguageUpdate();

}

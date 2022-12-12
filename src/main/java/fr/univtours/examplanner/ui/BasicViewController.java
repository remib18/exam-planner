package fr.univtours.examplanner.ui;

import fr.univtours.examplanner.Storage;
import javafx.fxml.FXML;

public abstract class BasicViewController {

	/**
	 * Initialise la vue<br/>
	 * Ne pas {@code Override}, utiliser {@link #init()} à la place
	 */
	@FXML
	private void initialize() {

		Storage.currentLanguageProperty().addListener((observable, oldValue, newValue) -> {
			onLanguageUpdate();
		});
		onLanguageUpdate();

		init();
	}

	/**
	 * Poursuit l'initialisation de la vue
	 */
	protected abstract void init();

	/**
	 * Appelé lorsque la langue est changée et au chargement de la vue
	 *
	 * @implNote Mettre ici tout le code relatif au chargement des traductions
	 */
	protected abstract void onLanguageUpdate();

}

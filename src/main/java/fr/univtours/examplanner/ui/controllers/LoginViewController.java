package fr.univtours.examplanner.ui.controllers;

import fr.univtours.examplanner.Storage;
import fr.univtours.examplanner.translations.SupportedLanguages;
import fr.univtours.examplanner.translations.Translation;
import fr.univtours.examplanner.ui.BasicViewController;
import fr.univtours.examplanner.ui.states.LoginState;
import fr.univtours.examplanner.utils.Ressource;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.jetbrains.annotations.NotNull;

import java.io.InputStream;

public class LoginViewController extends BasicViewController {

	/**
	 * Le titre sous l'icône de personne
	 */
	@FXML
	private Label title;

	/**
	 * Le bouton pour se connecter
	 */
	@FXML
	private Button signInBtn;

	/**
	 * La liste des langues
	 *
	 * @implNote Doit contenir au moins un élément : la langue en cours d'utilisation
	 */
	@FXML
	private VBox languageOptions;

	/**
	 * Traite le chic sur le bouton de connexion
	 */
	@FXML
	private void handleSignInBtnClick() {
		// Todo: implement
	}

	/**
	 * Change le statut du menu de langue
	 */
	private void switchLanguageMenu() {
		LoginState.setIsLanguageMenuOpen(!LoginState.getIsLanguageMenuOpen());
	}

	/**
	 * Traite le clic sur une langue pour changer la langue de l'application
	 *
	 * @param language La langue sélectionnée
	 */
	private void handleClickOnLanguage(SupportedLanguages language) {
		Translation.setLanguage(language);
	}

	/**
	 * Permet l'utilisation du menu des langues
	 */
	@Override
	protected void init() {
		languageOptions.setOnMouseClicked(event -> switchLanguageMenu());
		languageOptions.setAlignment(Pos.BASELINE_RIGHT);

		LoginState.isLanguageMenuOpenProperty().addListener((observable, oldValue, newValue) -> {
			buildLanguageOptions();
		});
		buildLanguageOptions();
	}

	/**
	 * @inheritDoc
	 */
	protected void onLanguageUpdate() {
		title.setText(Translation.get("ui.login.title"));
		signInBtn.setText(Translation.get("ui.login.button"));
	}

	/**
	 * Construit le menu des langues
	 */
	private void buildLanguageOptions() {
		// On vide la liste
		languageOptions.getChildren().clear();

		// On ajoute la langue en cours d'utilisation
		addCurrentLanguage();

		// On ajoute toutes les autres options si le menu est ouvert
		if (LoginState.getIsLanguageMenuOpen()) {
			for (SupportedLanguages language : SupportedLanguages.values()) {
				if (language != Storage.getCurrentLanguage()) {
					HBox flag = new HBox(buildLanguageFlag(language));
					flag.setOnMouseClicked(event -> handleClickOnLanguage(language));
					languageOptions.getChildren().add(flag);
				}
			}
		}
	}

	/**
	 * Ajoute la langue en cours d'utilisation au menu des langues
	 */
	private void addCurrentLanguage() {
		// Chargement de l'icône du menu (flèche bas ou fermer)
		ImageView icon = new ImageView(new Image(Ressource.getStream(
				"images/" + (LoginState.getIsLanguageMenuOpen() ? "close" : "arrow-down") + "-icon.png"
		)));
		icon.setFitWidth(12);
		icon.setPreserveRatio(true);

		// Création de l'item
		HBox item = new HBox(
				buildLanguageFlag(Storage.getCurrentLanguage()),
				icon
		);
		item.setAlignment(Pos.CENTER_LEFT);
		item.setSpacing(8);
		languageOptions.getChildren().add(item);
	}

	/**
	 * Construit l'icône de la langue
	 *
	 * @param language La langue
	 * @return L'icône de la langue
	 */
	private @NotNull ImageView buildLanguageFlag(@NotNull SupportedLanguages language) {
		InputStream image = Ressource.getStream(
				"images/" + language.shortHand() + (language == Storage.getCurrentLanguage() ? "-fill" : "") + "-icon.png"
		);
		ImageView flag = new ImageView();
		flag.setFitHeight(22);
		flag.setFitWidth(32);
		flag.setImage(new Image(image));
		return flag;
	}
}

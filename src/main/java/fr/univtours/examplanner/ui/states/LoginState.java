package fr.univtours.examplanner.ui.states;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.jetbrains.annotations.NotNull;

public class LoginState {

	/**
	 * État du menu de sélection de la langue
	 */
	private static final @NotNull ObjectProperty<Boolean> isLanguageMenuOpen = new SimpleObjectProperty<>(false);

	public static Boolean getIsLanguageMenuOpen() {
		return isLanguageMenuOpen.get();
	}

	public static void setIsLanguageMenuOpen(@NotNull Boolean isLanguageMenuOpen) {
		LoginState.isLanguageMenuOpen.set(isLanguageMenuOpen);
	}

	public static @NotNull ObjectProperty<Boolean> isLanguageMenuOpenProperty() {
		return isLanguageMenuOpen;
	}
}

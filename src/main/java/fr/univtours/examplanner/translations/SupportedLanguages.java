package fr.univtours.examplanner.translations;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

public enum SupportedLanguages {
	French,
	English;

	@Contract(pure = true)
	public static @NotNull @Unmodifiable List<String> getAll() {
		return List.of(
				French.toString(),
				English.toString()
		);
	}

	public static @NotNull SupportedLanguages fromString(@NotNull String s) {
		return switch (s) {
			case "French" -> French;
			case "English" -> English;
			default -> throw new IllegalArgumentException("No enum constant " + s);
		};
	}

}
package fr.univtours.examplanner.utils;

import fr.univtours.examplanner.entities.WithIDEntity;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EntityUtils {

	/**
	 * Convertie une liste d'entité en liste d'identifiants
	 *
	 * @param list La liste d'entité à convertir
	 * @return La liste des identifiants des entités
	 */
	public static @NotNull String listToIdString(@NotNull List<? extends WithIDEntity> list) {
		return listToString(list, WithIDEntity::getId);
	}

	/**
	 * Convertie une liste d'entité en une liste de <code>String</code>
	 *
	 * @param list     La liste d'entité à convertir
	 * @param toString La fonction permettant de convertir une entité en <code>String</code>
	 * @param <T>      Le type d'entité
	 * @return La liste de <code>String</code> correspondant aux entités
	 */
	public static <T> @NotNull String listToString(@NotNull List<T> list, @NotNull Function<T, String> toString) {
		return "[ " + list.stream()
				.map(toString)
				.collect(Collectors.joining(", ")) + " ]";
	}
}

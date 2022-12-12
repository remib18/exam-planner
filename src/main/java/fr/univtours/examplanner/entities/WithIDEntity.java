package fr.univtours.examplanner.entities;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public abstract class WithIDEntity {

	/**
	 * Identifiant du groupe dans la base de donnée<br/>
	 * Si null, le groupe n'est pas encore enregistré
	 */
	@Nullable
	protected String id;

	public WithIDEntity(@Nullable String id) {
		this.id = id;
	}

	public WithIDEntity() {
		this(null);
	}

	public @Nullable String getId() {
		return id;
	}

	public void setId(@NotNull String id) throws IllegalStateException {
		if (Objects.isNull(this.id)) {
			this.id = id;
			return;
		}
		throw new IllegalStateException("Cannot change the id of a saved entity.");
	}
}

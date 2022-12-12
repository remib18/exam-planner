package fr.univtours.examplanner.entities.dtos;

import fr.univtours.examplanner.entities.WithIDEntity;
import fr.univtours.examplanner.enums.Civility;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class ManagerDTO extends WithIDEntity {

	/**
	 * Civilité du manager
	 */
	@NotNull
	private Civility civility;

	/**
	 * Nom de famille du manager
	 */
	@NotNull
	private String lastName;

	/**
	 * Prénom du manager
	 */
	@NotNull
	private String firstName;

	public ManagerDTO(
			@Nullable String id,
			@NotNull Civility civility,
			@NotNull String lastName,
			@NotNull String firstName
	) {
		super(id);
		this.civility = civility;
		this.lastName = lastName;
		this.firstName = firstName;
	}

	public @NotNull Civility getCivility() {
		return civility;
	}

	public void setCivility(@NotNull Civility civility) {
		this.civility = civility;
	}

	public @NotNull String getLastName() {
		return lastName;
	}

	public void setLastName(@NotNull String lastName) {
		this.lastName = lastName;
	}

	public @NotNull String getFirstName() {
		return firstName;
	}

	public void setFirstName(@NotNull String firstName) {
		this.firstName = firstName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ManagerDTO that = (ManagerDTO) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, civility, lastName, firstName);
	}

	@Override
	public String toString() {
		return "ManagerDTO{" +
				"\n\tid: " + id +
				", \n\tcivility: " + civility +
				", \n\tlastName: " + lastName +
				", \n\tfirstName: " + firstName +
				"\n}";
	}
}
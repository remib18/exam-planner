package fr.univtours.examplanner.entities.dtos;

import fr.univtours.examplanner.controllers.ManagerController;
import fr.univtours.examplanner.entities.EditableEntity;
import fr.univtours.examplanner.entities.WithIDEntity;
import fr.univtours.examplanner.enums.Civility;
import fr.univtours.examplanner.exceptions.ControllerException;
import javafx.beans.property.SimpleObjectProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class ManagerDTO extends WithIDEntity implements EditableEntity {

	/**
	 * Civilité du manager
	 */
	@NotNull
	private final SimpleObjectProperty< @NotNull Civility > civility = new SimpleObjectProperty<>();

	/**
	 * Nom de famille du manager
	 */
	@NotNull
	private final SimpleObjectProperty< @NotNull String > lastName = new SimpleObjectProperty<>();

	/**
	 * Prénom du manager
	 */
	@NotNull
	private final SimpleObjectProperty< @NotNull String > firstName = new SimpleObjectProperty<>();

	public ManagerDTO(
			@Nullable String id,
			@NotNull Civility civility,
			@NotNull String lastName,
			@NotNull String firstName
	) {
		super(id);
		this.civility.set(civility);
		this.lastName.set(lastName);
		this.firstName.set(firstName);
    }

    public @NotNull Civility getCivility() {
        return civility.get();
    }

    public void setCivility( @NotNull Civility civility ) {
        this.civility.set(civility);
    }

    public SimpleObjectProperty< Civility > civilityProperty() {return civility;}

	public @NotNull String getLastName() {
		return lastName.get();
	}

	public void setLastName( @NotNull String lastName ) {
		this.lastName.set(lastName);
	}

	public SimpleObjectProperty< String > lastNameProperty() {return lastName;}

	public @NotNull String getFirstName() {
		return firstName.get();
	}

	public void setFirstName( @NotNull String firstName ) {
		this.firstName.set(firstName);
	}

	public @NotNull SimpleObjectProperty< @NotNull String > firstNameProperty() {
		return firstName;
	}

	@Override
	public boolean equals( Object o ) {
		if ( this == o ) return true;
		if ( null == o || getClass() != o.getClass() ) return false;
		ManagerDTO that = (ManagerDTO) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, civility, lastName, firstName);
	}

	@Override
	public String toString() {
		return "ManagerDTO{" + "\n\tid: " +
			   id +
			   ", \n\tcivility: " +
			   civility +
			   ", \n\tlastName: " +
			   lastName +
			   ", \n\tfirstName: " +
			   firstName +
			   "\n}";
	}

	@Override
	public void set( String property, Object value ) throws ControllerException {
		switch ( property ) {
			case "civility" -> setCivility((Civility) value);
			case "lastname" -> setLastName((String) value);
			case "firstname" -> setFirstName((String) value);
			default -> throw new IllegalArgumentException("Unknown property " + property);
		}
		if ( Objects.nonNull(id.get()) ) {
			ManagerController.save(this);
		}
	}
}
package fr.univtours.examplanner.entities.dtos;

import fr.univtours.examplanner.entities.EditableEntity;
import fr.univtours.examplanner.exceptions.ControllerException;
import javafx.beans.property.SimpleObjectProperty;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DepartmentDTO implements EditableEntity {

	/**
	 * Maquettes gérées par le département
	 */
	private final SimpleObjectProperty< @NotNull List< @NotNull String > > MockUpDTOs =
			new SimpleObjectProperty<>(new ArrayList<>());
	/**
	 * Nom du département
	 */
	@NotNull
	private final SimpleObjectProperty< @NotNull String > name = new SimpleObjectProperty<>();

	public DepartmentDTO(@NotNull String name) {
		this.name.set(name);
	}

	public @NotNull String getName() {
		return name.get();
	}

	public void setName(@NotNull String name) {
		this.name.set(name);
	}

	public @NotNull List< @NotNull String > getMockUpDTOs() { return MockUpDTOs.get();
	}

	public void addMockUpDTO(@NotNull MockUpDTO MockUpDTO) { this.MockUpDTOs.get().add(MockUpDTO.getId()); }

	public void addMockUpDTO(@NotNull List<MockUpDTO> MockUpDTOs) { this.MockUpDTOs.get().addAll(getMockUpDTOs()); }

	public void removeMockUpDTO(@NotNull MockUpDTO MockUpDTO) {
		this.MockUpDTOs.get().remove(MockUpDTO);
	}

	public void removeMockUpDTO(@NotNull List<MockUpDTO> MockUpDTOs) {
		this.MockUpDTOs.get().removeAll(MockUpDTOs);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if ( null == o || getClass() != o.getClass()) return false;
		DepartmentDTO that = (DepartmentDTO) o;
		return Objects.equals(name, that.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, MockUpDTOs);
	}

	@Override
	public String toString() {
		return "DepartmentDTO{" +
				", \n\tname: " + name +
				", \n\tMockUpDTOs: " + MockUpDTOs +
				"\n}";
	}

	@Override
	public void set( String property, Object value ) throws ControllerException {
	}
}

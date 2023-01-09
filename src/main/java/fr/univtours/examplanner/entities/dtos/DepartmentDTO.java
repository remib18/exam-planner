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
    @NotNull
    private final List< MockUpDTO > MockUpDTOs = new ArrayList<>();

    /**
     * Nom du département
     */
    @NotNull
    private final SimpleObjectProperty< @NotNull String > name = new SimpleObjectProperty<>();

    public DepartmentDTO( @NotNull String name ) {
        this.name.set(name);
    }

    public SimpleObjectProperty< String > nameProperty() {
        return name;
    }

    public @NotNull String getName() {
        return name.get();
    }

    public void setName( @NotNull String name ) {
        if ( this.name.get().equals("<name>") ) {
            this.name.set(name);
        }
    }

    public @NotNull List< MockUpDTO > getMockUpDTOs() {
        return MockUpDTOs;
    }

    public void addMockUpDTO( @NotNull MockUpDTO MockUpDTO ) {
        this.MockUpDTOs.add(MockUpDTO);
    }

    public void addMockUpDTO( @NotNull List< MockUpDTO > MockUpDTOs ) {
		this.MockUpDTOs.addAll(MockUpDTOs);
	}

	public void removeMockUpDTO(@NotNull MockUpDTO MockUpDTO) {
		this.MockUpDTOs.remove(MockUpDTO);
	}

	public void removeMockUpDTO(@NotNull List<MockUpDTO> MockUpDTOs) {
		this.MockUpDTOs.removeAll(MockUpDTOs);
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
        return "DepartmentDTO{" + ", \n\tname: " + name + ", \n\tMockUpDTOs: " + MockUpDTOs + "\n}";
    }

    @Override
    public void set( String property, Object value ) throws ControllerException {
		if ( property.equals("name") ) {
            setName((String) value);
		} else {
			throw new UnsupportedOperationException("");
		}
    }
}

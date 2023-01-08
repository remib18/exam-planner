package fr.univtours.examplanner.entities.dtos;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DepartmentDTO {

	/**
	 * Maquettes gérées par le département
	 */
	@NotNull
	private final List<MockUpDTO> MockUpDTOs = new ArrayList<>();

	/**
	 * Nom du département
	 */
	@NotNull
	private String name;

	public DepartmentDTO(@NotNull String name) {
		this.name = name;
	}

	public @NotNull String getName() {
		return name;
	}

	public void setName(@NotNull String name) {
		this.name = name;
	}

	public @NotNull List<MockUpDTO> getMockUpDTOs() {
		return MockUpDTOs;
	}

	public void addMockUpDTO(@NotNull MockUpDTO MockUpDTO) {
		this.MockUpDTOs.add(MockUpDTO);
	}

	public void addMockUpDTO(@NotNull List<MockUpDTO> MockUpDTOs) {
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
		if (o == null || getClass() != o.getClass()) return false;
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
}

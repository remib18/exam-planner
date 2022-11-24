package fr.univtours.examplanner.entities.dtos;

import fr.univtours.examplanner.entities.WithIDEntity;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DepartmentDTO extends WithIDEntity {

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

    public DepartmentDTO(@Nullable String id, @NotNull String name) {
        super(id);
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
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, MockUpDTOs);
    }

    @Override
    public String toString() {
        return "DepartmentDTO{" +
                "\n\tid: " + id +
                ", \n\tname: " + name +
                ", \n\tMockUpDTOs: " + MockUpDTOs +
                "\n}";
    }
}

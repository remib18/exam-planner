package fr.univtours.examplanner.repositories;

import fr.univtours.examplanner.entities.dtos.DepartmentDTO;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class DepartmentRepo implements BaseRepo<DepartmentDTO, String> {


    @Override
    public @NotNull DepartmentDTO save(@NotNull DepartmentDTO entity) {
        // TODO implement here
        throw new UnsupportedOperationException();
    }

    @Override
    public @NotNull List<DepartmentDTO> getAll() {
        // TODO implement here
        return new ArrayList<>();
    }

    @Override
    public @NotNull DepartmentDTO getById(@NotNull String id) {
        // TODO implement here
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(@NotNull DepartmentDTO entity) {
        // TODO implement here
        return false;
    }

}
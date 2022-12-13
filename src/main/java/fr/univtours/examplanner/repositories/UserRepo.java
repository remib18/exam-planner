package fr.univtours.examplanner.repositories;

import fr.univtours.examplanner.entities.dtos.DepartmentDTO;
import fr.univtours.examplanner.entities.dtos.UserDTO;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class UserRepo implements BaseRepo<UserDTO, String> {

    /**
     * Récupère tous utilisateurs qui ne sont pas des administrateurs
     *
     * @return la liste des utilisateurs correspondants
     */
    public @NotNull List<UserDTO> getAllNonAdmin() {
        // TODO implement here
        return new ArrayList<>();
    }

    /**
     * Récupère tous les utilisateurs qui sont des administrateurs
     * @return la liste des utilisateurs correspondants
     */
    public @NotNull List<UserDTO> getAllAdmin() {
        // TODO implement here
        return new ArrayList<>();
    }

    /**
     * Récupère tous les utilisateurs qui font partis du département en paramètre
     * @param dep un département
     * @return la liste des utilisateurs correspondants
     */
    public @NotNull List<UserDTO> getAllFromDepartment(@NotNull DepartmentDTO dep) {
        // TODO implement here
        return new ArrayList<>();
    }

    @Override
    public @NotNull UserDTO save(@NotNull UserDTO entity) {
        // TODO implement here
        throw new UnsupportedOperationException();
    }

   @Override
    public @NotNull List<UserDTO> getAll() {
        // TODO implement here
        return new ArrayList<>();
    }

    @Override
    public @NotNull UserDTO getById(@NotNull String id) {
        // TODO implement here
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(@NotNull UserDTO entity) {
        // TODO implement here
        return false;
    }

}
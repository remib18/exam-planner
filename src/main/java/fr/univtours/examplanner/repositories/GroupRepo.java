package fr.univtours.examplanner.repositories;

import fr.univtours.examplanner.entities.dtos.GroupDTO;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class GroupRepo implements BaseRepo<GroupDTO, String> {

    /**
     * Récupère tous les sous-groupes du groupe passé en paramètre
     *
     * @param group un groupe
     * @return la liste des groupes correspondants
     */
    public @NotNull List<GroupDTO> getAllFromGroup(@NotNull GroupDTO group) {
        // TODO implement here
        return new ArrayList<>();
    }

    /**
     * Récupère tous les examens
     *
     * @param e un examen
     * @return la liste des examens correspondants
     */
    public @NotNull List<GroupDTO> getAllFromExam(GroupDTO e) {
        // TODO implement here
        return new ArrayList<>();
    }

    @Override
    public @NotNull GroupDTO save(@NotNull GroupDTO entity) {
        // TODO implement here
        throw new UnsupportedOperationException();
    }

    @Override
    public @NotNull List<GroupDTO> getAll() {
        // TODO implement here
        return new ArrayList<>();
    }

    @Override
    public @NotNull GroupDTO getById(@NotNull String id) {
        // TODO implement here
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(@NotNull GroupDTO entity) {
        // TODO implement here
        return false;
    }

}
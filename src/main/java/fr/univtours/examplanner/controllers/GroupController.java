package fr.univtours.examplanner.controllers;

import fr.univtours.examplanner.entities.dtos.GroupDTO;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class GroupController implements BaseController<GroupDTO> {

    // todo(@benoît): Repo
    /**
     * Interface avec la base de données permettant d'effectuer des opérations standards sur les groupes
     */
    @NotNull
    private final Object repo;

    public GroupController() {
        repo = new Object();
    }

    /**
     * Liste les groupes
     *
     * @return la liste des groupes
     */
    public @NotNull List<GroupDTO> getAll() {
        // TODO implement here
        return new ArrayList<>();
    }

    /**
     * Crée un groupe
     *
     * @param name        le nom du groupe
     * @param size        la taille du groupe
     * @param subGroupIds la liste des identifiants des sous-groupes
     * @return le groupe créé
     */
    public @NotNull GroupDTO create(@NotNull String name, int size, @NotNull List<Integer> subGroupIds) {
        // TODO implement here
        throw new UnsupportedOperationException();
    }

    /**
     * Modifie un groupe
     *
     * @param entity le groupe à modifier
     */
    public void save(@NotNull GroupDTO entity) throws Exception {
        // TODO implement here
        throw new Exception();
    }

    /**
     * Supprime un groupe
     *
     * @param entity le groupe à supprimer
     */
    public void delete(@NotNull GroupDTO entity) throws Exception {
        // TODO implement here
        throw new Exception();
    }


}
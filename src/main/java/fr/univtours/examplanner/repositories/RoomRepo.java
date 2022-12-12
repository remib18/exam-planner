package fr.univtours.examplanner.repositories;

import fr.univtours.examplanner.entities.dtos.RoomDTO;
import fr.univtours.examplanner.enums.RoomType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class RoomRepo implements BaseRepo<RoomDTO, String> {

    // todo(@remi): Fix Constraint type
    /**
     * Récupère les salles qui ont le type en paramètre
     * @param constraint une contrainte de salle
     * @return la liste des salles correspondante a la contrainte
     */
    public @NotNull List<RoomDTO> getAllWithConstraint(@NotNull RoomType constraint) {
        // TODO implement here
        return new ArrayList<>();
    }

    /**
     * Récupère les salles qui ont les contraintes en paramètre
     * @param constraints une liste de contraintes
     * @return la liste de salles correspondantes aux contraintes
     */
    public @NotNull List<RoomDTO> getAllWithConstraints(@NotNull List<RoomType> constraints) {
        // TODO implement here
        return new ArrayList<>();
    }

   @Override
    public @NotNull RoomDTO save(@NotNull RoomDTO entity) {
        // TODO implement here
       throw new UnsupportedOperationException();    }

  @Override
    public @NotNull List<RoomDTO> getAll() {
        // TODO implement here
        return new ArrayList<>();
    }

   @Override
    public @NotNull RoomDTO getById(@NotNull String id) {
        // TODO implement here
       throw new UnsupportedOperationException();
    }

   @Override
    public boolean delete(@NotNull RoomDTO entity) {
        // TODO implement here
        return false;
    }

}
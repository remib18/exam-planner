package fr.univtours.examplanner.repositories;

import fr.univtours.examplanner.entities.dtos.ManagerDTO;
import fr.univtours.examplanner.entities.dtos.RoomDTO;
import fr.univtours.examplanner.entities.dtos.SlotDTO;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class SlotRepo implements BaseRepo<SlotDTO, String> {

    /**
     * Récupère tous les créneaux parmi une salle
     *
     * @param room une salle
     * @return la liste des créneaux correspondants
     */
    public @NotNull List<SlotDTO> getAllFromRoom(@NotNull RoomDTO room) {
        // TODO implement here
        return new ArrayList<>();
    }

    /**
     * Récupère tous les créneaux parmi un manager
     *
     * @param manager un manager
     * @return la liste des créneaux correspondants
     */
    public @NotNull List<SlotDTO> getAllFromManager(@NotNull ManagerDTO manager) {
        // TODO implement here
        return new ArrayList<>();
    }

    @Override
    public @NotNull SlotDTO save(@NotNull SlotDTO entity) {
        // TODO implement here
        throw new UnsupportedOperationException();
    }

    @Override
    public @NotNull List<SlotDTO> getAll() {
        // TODO implement here
        return new ArrayList<>();
    }

   @Override
    public @NotNull SlotDTO getById(@NotNull String id) {
        // TODO implement here
       throw new UnsupportedOperationException();    }

   @Override
    public boolean delete(@NotNull SlotDTO entity) {
        // TODO implement here
        return false;
    }

}
package fr.univtours.examplanner.controllers;

import fr.univtours.examplanner.entities.dtos.SlotDTO;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SlotController implements BaseController<SlotDTO> {

    // todo(@benoît): Repo
    /**
     * Interface avec la base de données permettant d'effectuer des opérations standards sur les horaires
     */
    @NotNull
    private final Object repo;

    public SlotController() {
        repo = new Object();
    }

    /**
     * Liste les horaires
     *
     * @return la liste des horaires
     */
    public @NotNull List<SlotDTO> getAll() {
        // TODO implement here
        return new ArrayList<>();
    }

    /**
     * Crée un horaire
     *
     * @param day           le jour du créneau
     * @param hour          l'heure du créneau
     * @param durationFloat la durée du créneau
     * @return l'horaire créé
     */
    public SlotDTO create(@NotNull LocalDateTime day, @NotNull LocalDateTime hour, @NotNull Float durationFloat) {
        // TODO implement here
        throw new UnsupportedOperationException();
    }

    /**
     * Modifie l'horaire
     *
     * @param entity l'horaire à modifier
     */
    public void save(@NotNull SlotDTO entity) throws Exception {
        // TODO implement here
        throw new Exception();
    }

    /**
     * Supprime un horaire
     *
     * @param entity l'horaire à modifier
     */
    public void delete(@NotNull SlotDTO entity) throws Exception {
        // TODO implement here
        throw new Exception();
    }

}
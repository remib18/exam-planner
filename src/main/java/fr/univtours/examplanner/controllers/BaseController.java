package fr.univtours.examplanner.controllers;

import fr.univtours.examplanner.exceptions.ControllerException;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface BaseController<T> {

    /**
     * Liste les entités
     *
     * @return la liste
     */
    public @NotNull List<T> getAll() throws ControllerException;

    /**
     * Met à jour une entité
     *
     * @param entity une entité
     */
    public void save(@NotNull T entity) throws Exception;

    /**
     * Supprime une entité
     *
     * @param entity une entité
     */
    public void delete(@NotNull T entity) throws Exception;

}
package fr.univtours.examplanner.controllers;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface BaseController<T> {

    /**
     * Liste les entités
     *
     * @return la liste
     */
    public @NotNull List<T> getAll();

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
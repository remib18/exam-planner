package fr.univtours.examplanner.controllers;

import org.jetbrains.annotations.NotNull;

public interface BaseController<T> {

    /**
     * Met à jour une entité
     *
     * @param entity une entité
     */
    void save( @NotNull T entity ) throws Exception;

    /**
     * Supprime une entité
     *
     * @param entity une entité
     */
    void delete( @NotNull T entity ) throws Exception;

}
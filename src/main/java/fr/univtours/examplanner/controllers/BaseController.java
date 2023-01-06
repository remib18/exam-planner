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
    @NotNull List< T > getAll() throws ControllerException;

    /**
     * Met à jour une entité
     *
     * @param entity une entité
     */
    void save( @NotNull T entity ) throws ControllerException;

    /**
     * Supprime une entité
     *
     * @param entity une entité
     */
    void delete( @NotNull T entity ) throws ControllerException;

}
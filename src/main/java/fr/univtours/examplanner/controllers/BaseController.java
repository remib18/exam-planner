package fr.univtours.examplanner.controllers;

import fr.univtours.examplanner.exceptions.ControllerException;
import fr.univtours.examplanner.exceptions.RepoException;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface BaseController<T> {

    /**
     * Liste les entités
     *
     * @return la liste
     */
    @NotNull List< T > getAll() throws RepoException, ControllerException;

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
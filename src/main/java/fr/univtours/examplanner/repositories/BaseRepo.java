package fr.univtours.examplanner.repositories;


import java.util.List;

public interface BaseRepo<Entity, PK> {

    /**
     * Permet de sauvegarder une entité mise en paramètre
     *
     * @param entity l'entité à sauvegarder
     * @return l'entité sauvegardée
     */
    Entity save(Entity entity);

    /**
     * Permet d'obtenir toutes les informations sur une table
     *
     * @return une liste d'entité de toutes les informations
     */
    List<Entity> getAll();

    /**
     * Permet d'obtenir une entité grâce à son identifiant
     *
     * @param id l'identifiant
     * @return l'entité qui correspond à l'identifiant
     */
    Entity getById(PK id);

    /**
     * Permet de supprimer une entité
     *
     * @param entity l'entité à supprimer
     * @return vraie si l'entité à bien été supprimé
     */
    boolean delete(Entity entity);

}
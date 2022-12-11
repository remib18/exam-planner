package fr.univtours.examplanner.repositories;


import java.util.List;

public interface BaseRepo<Entity, PK> {

    /**
     *
     * @param entity
     * @return
     */
    public Entity save(Entity entity);

    /**
     * @return
     */
    public List<Entity> getAll();

    /**
     * @param id 
     * @return
     */
    public Entity getById(PK id);

    /**
     * @param entity
     * @return
     */
    public boolean delete(Entity entity);

}
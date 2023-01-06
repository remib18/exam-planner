package fr.univtours.examplanner.repositories;

import fr.univtours.examplanner.entities.dtos.SubjectDTO;
import fr.univtours.examplanner.exceptions.DatabaseConnectionException;
import fr.univtours.examplanner.mappers.SubjectMapper;
import fr.univtours.examplanner.utils.Database;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class SubjectRepo implements BaseRepo< SubjectDTO, String > {

    /**
     * Permet d'obtenir toutes les matières
     *
     * @return une liste de matières
     *
     * @throws RepoException si une erreur survient lors de la récupération
     */

    @Override
    public @NotNull List< SubjectDTO > getAll() throws RepoException {
        return getAllFrom(null, null);
    }

    /**
     * Récupère la liste des matières selon les paramètres fournit
     *
     * @param key   Nom de la colonne à filtrer
     * @param value Valeur de la colonne à filtrer
     * @throws RepoException si une erreur survient
     */

    private @NotNull List< SubjectDTO > getAllFrom( @Nullable String key, @Nullable String value )
    throws RepoException {
        String sql = "SELECT * FROM subject";
        boolean withOptions = !Objects.isNull(key) && !Objects.isNull(value);
        if ( withOptions ) {
            sql += " WHERE " + key + " = ?";
        }
        try ( PreparedStatement stm = Database.getConnection().prepareStatement(sql) ) {
            if ( withOptions ) {
                stm.setString(1, value);
            }
            return SubjectMapper.entityToDo(stm.executeQuery());
        } catch ( SQLException | DatabaseConnectionException | MappingException e ) {
            throw new RepoException("Getting subjects failed, no rows affected", e);
        }
    }

    /**
     * Permet de sauvegarde une matière
     *
     * @param entity La matière à sauvegarder
     * @return La matière sauvegarder
     *
     * @throws RepoException si une erreur survient lors de la sauvegarde
     */

    @Override
    public @NotNull SubjectDTO save( @NotNull SubjectDTO entity ) throws RepoException {
        SubjectDTO alreadyExisting = getByName(entity.getId());
        if ( !Objects.isNull(alreadyExisting) &&
             !Objects.isNull(alreadyExisting.getId()) &&
             !alreadyExisting.getId().equals(entity.getId()) ) {
            throw new RepoException("A subject with same id already exist", null);
        }
        boolean hasId = !Objects.isNull(entity.getId());
        String id = hasId ? entity.getId() : Database.getNewUUID();
        String sql;
        if ( hasId ) {
            sql = "UPDATE subject SET name = ? WHERE id = '" + id + "'";
        } else {
            sql = "INSERT INTO subject (id, name) VALUES ('" + id + "', ?)";
        }
        try ( PreparedStatement stm = Database.getConnection().prepareStatement(sql) ) {
            stm.setString(1, entity.getName());
            int rows = stm.executeUpdate();
            if ( 0 == rows ) {
                throw new RepoException("Saving subject failed, no rows affected", null);
            }
            if ( !hasId ) {
                entity.setId(id);
            }
            return entity
        } catch ( SQLException | DatabaseConnectionException e ) {
            throw new RepoException("Saving subject failed, no rows affected", e);
        }
    }

    /**
     * Permet d'obtenir une matière grâce à son nom
     *
     * @param name Le nom de la matière
     * @return La matière correspondante au nom
     *
     * @throws RepoException Si une erreur survient
     */

    public @Nullable SubjectDTO getByName( @NotNull String name ) throws RepoException {
        List< SubjectDTO > result = getAllFrom("name", name);
        if ( result.isEmpty() ) {
            return null;
        }
        return result.get(0);
    }

    /**
     * Permet d'obtenir une matière grâce à son identifiant
     *
     * @param id l'identifiant
     * @return la matière correspondante à l'identifiant
     *
     * @throws RepoException Si une erreur survient
     */

    public @Nullable SubjectDTO getById( @NotNull String id ) throws RepoException {
        List< SubjectDTO > result = getAllFrom("id", id);
        if ( result.isEmpty() ) {
            return null;
        }
        return result.get(0);
    }

    @Override
    public boolean delete( @NotNull SubjectDTO entity ) throws RepoException {
        String sql = "DELETE FROM subject WHERE id = ?";
        try ( PreparedStatement stm = Database.getConnection().prepareStatement(sql) ) {
            stm.setString(1, entity.getId());
            int rows = stm.executeUpdate();
            return 0 != rows;
        } catch ( SQLException | DatabaseConnectionException e ) {
            throw new RepoException("Delete subject failed, no rows affected", e);
        }
    }

}

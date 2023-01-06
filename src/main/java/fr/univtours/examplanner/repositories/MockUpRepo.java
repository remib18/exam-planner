package fr.univtours.examplanner.repositories;

import fr.univtours.examplanner.entities.dtos.MockUpDTO;
import fr.univtours.examplanner.enums.Degree;
import fr.univtours.examplanner.exceptions.DatabaseConnectionException;
import fr.univtours.examplanner.exceptions.MappingException;
import fr.univtours.examplanner.exceptions.RepoException;
import fr.univtours.examplanner.mappers.MockUpMapper;
import fr.univtours.examplanner.utils.Database;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class MockUpRepo implements BaseRepo< MockUpDTO, String > {

    /**
     * Permet d'obtenir tous les MockUp
     *
     * @return Une liste de MockUp
     *
     * @throws RepoException Si une erreur survient lors de la réception
     */

    @Override
    public @NotNull List< MockUpDTO > getAll() throws RepoException {
        return getAllFrom(null, null);
    }

    /**
     * Récupère une liste de MockUp selon les paramètres fournit
     *
     * @param key   Nom de la colonne à filter
     * @param value Valeur de la colonne à filter
     * @return La liste des MockUp correspondants
     *
     * @throws RepoException Si une erreur survient
     */

    private @NotNull List< MockUpDTO > getAllFrom( @Nullable String key, @Nullable String value ) throws RepoException {
        String sql = "SELECT * FROM mockup";
        boolean withOptions = !Objects.isNull(key) && !Objects.isNull(value);
        if ( withOptions ) {
            sql += " WHERE " + key + " = ?";
        }
        try ( PreparedStatement stm = Database.getConnection().prepareStatement(sql) ) {
            if ( withOptions ) {
                stm.setString(1, value);
            }
            return MockUpMapper.entityToDTO(stm.executeQuery());
        } catch ( SQLException | DatabaseConnectionException | MappingException e ) {
            throw new RepoException("Getting MockUp failed, no rows affected", e);
        }
    }

    /**
     * Permet de sauvegarder un MockUp
     *
     * @param entity L'entité à sauvegarder
     * @return La MockUp à sauvegarder
     *
     * @throws RepoException Si une erreur survient lors de la sauvegarde
     */

    @Override
    public @NotNull MockUpDTO save( @NotNull MockUpDTO entity ) throws RepoException {
        MockUpDTO alreadyExisting = getByName(entity.getName());
        if ( !Objects.isNull(alreadyExisting) &&
             !Objects.isNull(alreadyExisting.getId()) &&
             !alreadyExisting.getId().equals(entity.getId()) ) {
            throw new RepoException("A mockUp with the same name already exists", null);
        }
        boolean hasId = !Objects.isNull(entity.getId());
        String id = hasId ? entity.getId() : Database.getNewUUID();
        String sql;
        if ( hasId ) {
            sql = "UPDATE mockup SET name = ?, degree = ?, semester = ?, subjects = ? WHERE id = '" + id + "'";
        } else {
            sql = "INSERT INTO mockup (id, name, degree, semester, subjects) VALUES ('" + id + "', ?, ?, ?, ?)";
        }
        try ( PreparedStatement stm = Database.getConnection().prepareStatement(sql) ) {
            stm.setString(1, entity.getName());
            stm.setObject(2, entity.getDegree());
            stm.setInt(3, entity.getSemester());
            stm.setObject(4, entity.getSubjects());
            int rows = stm.executeUpdate();
            if ( 0 == rows ) {
                throw new RepoException("Saving MockUp failed, no rows affected", null);
            }
            if ( !hasId ) {
                entity.setId(id);
            }
            return entity;
        } catch ( SQLException | DatabaseConnectionException e ) {
            throw new RepoException("Saving MockUp failed, no rows affected", e);
        }
    }

    /**
     * Permet d'obtenir un MockUp grâce à son nom
     *
     * @param name Le nom du MockUp
     * @return Le MockUp correspondant au nom
     *
     * @throws RepoException Si une erreur survient
     */

    public @Nullable MockUpDTO getByName( @NotNull String name ) throws RepoException {
        List< MockUpDTO > result = getAllFrom("name", name);
        if ( result.isEmpty() ) {
            return null;
        }
        return result.get(0);
    }

    /**
     * Permet de supprimer un MockUp
     *
     * @param entity L'entité à supprimer
     * @return vrai si l'utilisateur a bien été supprimé
     *
     * @throws RepoException Si une erreur survient
     */

    @Override
    public boolean delete( @NotNull MockUpDTO entity ) throws RepoException {
        String sql = "DELETE FROM mockup WHERE id = ?";
        try ( PreparedStatement stm = Database.getConnection().prepareStatement(sql) ) {
            stm.setString(1, entity.getId());
            int rows = stm.executeUpdate();
            return 0 != rows;
        } catch ( SQLException | DatabaseConnectionException e ) {
            throw new RepoException("Delete MockUp failed, no rows affected", e);
        }
    }

    /**
     * Permet d'obtenir un MockUp par son identifiant
     *
     * @param id L'identifiant du MockUp
     * @return Le MockUp correspondant  à l'identifiant
     *
     * @throws RepoException Si une erreur survient
     */

    @Override
    public @Nullable MockUpDTO getById( @NotNull String id ) throws RepoException {
        List< MockUpDTO > result = getAllFrom("id", id);
        if ( result.isEmpty() ) {
            return null;
        }
        return result.get(0);
    }

    /**
     * Permet d'obtenir un MockUp grâce à son niveau
     *
     * @param degree Le niveau du MockUp
     * @return Le MockUp correspondant au niveau
     *
     * @throws RepoException Si une erreur survient
     */

    public MockUpDTO getByDegree( @NotNull Degree degree ) throws RepoException {
        List< MockUpDTO > result = getAllFrom("degree", degree);
        if ( result.isEmpty() ) {
            return null;
        }
        return result.get(0);
    }

    /**
     * Permet d'obtenir un MockUp grâce au semestre
     *
     * @param semester Le semestre du MockUp
     * @return Le MockUp correspondant
     *
     * @throws RepoException Si une erreur survient
     */

    public MockUpDTO getBySemester( int semester ) throws RepoException {
        List< MockUpDTO > result = getAllFrom("semester", String.valueOf(semester));
        if ( result.isEmpty() ) {
            return null;
        }
        return result.get(0);
    }

}
